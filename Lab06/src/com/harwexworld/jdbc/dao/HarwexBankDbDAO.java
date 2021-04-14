package com.harwexworld.jdbc.dao;

import com.harwexworld.dao.DAO;
import com.harwexworld.jdbc.IConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public abstract class HarwexBankDbDAO<Entity, Key> implements DAO<Entity, Key> {
    protected final IConnector connector;

    public HarwexBankDbDAO(IConnector connector, String connectionUrl) {
        this.connector = connector;
        this.connector.createConnection(connectionUrl);
    }

    @Override
    public void create(Entity model) {
        try (PreparedStatement statement = connector.getConnection().prepareStatement(CreateInsertQuery())) {
            FillStatement(statement, model);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            connector.closeConnection();
        }
    }

    @Override
    public Entity readByKey(Key key) {
        Entity model = GetModel();

        try (PreparedStatement statement = connector.getConnection().prepareStatement(CreateSelectQuery())) {
            SetKey(statement, model, 1);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                FillModelFromResultSet(resultSet, model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            connector.closeConnection();
        }
        return model;
    }

    @Override
    public void update(Entity model) {
        try (PreparedStatement statement = connector.getConnection().prepareStatement(CreateUpdateQuery())) {
            FillStatement(statement, model);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            connector.closeConnection();
        }
    }

    @Override
    public void delete(Entity model) {
        try (PreparedStatement statement = connector.getConnection().prepareStatement(CreateDeleteQuery())) {
            SetKey(statement, model, 1);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            connector.closeConnection();
        }
    }

    private String CreateInsertQuery() {
        var strBuffer = new StringBuilder(SqlViewDAO.INSERT.QUERY);
        strBuffer.insert(strBuffer.indexOf("insertValue"), GetInsertQuery().get("insertValue"));
        strBuffer.insert(strBuffer.indexOf("valuesValue"), GetInsertQuery().get("valuesValue"));
        return strBuffer.toString().
                replace("insertValue", "").
                replace("valuesValue", "");
    }

    private String CreateSelectQuery() {
        var strBuffer = new StringBuilder(SqlViewDAO.SELECT.QUERY);
        strBuffer.insert(strBuffer.indexOf("selectValue"), GetSelectQuery().get("selectValue"));
        strBuffer.insert(strBuffer.indexOf("fromValue"), GetSelectQuery().get("fromValue"));
        return strBuffer.toString().
                replace("selectValue", "").
                replace("fromValue", "");
    }

    private String CreateUpdateQuery() {
        var strBuffer = new StringBuilder(SqlViewDAO.UPDATE.QUERY);
        strBuffer.insert(strBuffer.indexOf("updateFromValue"), GetUpdateQuery().get("updateFromValue"));
        strBuffer.insert(strBuffer.indexOf("setValue"), GetUpdateQuery().get("setValue"));
        return strBuffer.toString().
                replace("updateFromValue", "").
                replace("setValue", "");
    }

    private String CreateDeleteQuery() {
        var strBuffer = new StringBuilder(SqlViewDAO.DELETE.QUERY);
        strBuffer.insert(strBuffer.indexOf("deleteFromValue"), GetDeleteQuery().get("deleteFromValue"));
        return strBuffer.toString().replace("deleteFromValue", "");
    }

    protected abstract Entity GetModel();
    protected abstract HashMap<String, String> GetInsertQuery();
    protected abstract HashMap<String, String> GetSelectQuery();
    protected abstract HashMap<String, String> GetUpdateQuery();
    protected abstract HashMap<String, String> GetDeleteQuery();
    protected abstract void FillStatement(PreparedStatement statement, Entity model);
    protected abstract void FillModelFromResultSet(ResultSet resultSet, Entity model);
    protected abstract void SetKey(PreparedStatement statement, Entity model, int positionKey);

    enum SqlViewDAO {
        INSERT("INSERT INTO insertValue VALUES (valuesValue)"),

        SELECT ("SELECT selectValue FROM fromValue WHERE u.Id = (?)"),

        UPDATE("UPDATE updateFromValue SET setValue WHERE Id = (?)"),

        DELETE("DELETE FROM deleteFromValue WHERE Id = (?)");

        String QUERY;

        SqlViewDAO(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}
