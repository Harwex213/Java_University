package com.harwexworld.jdbc.dao;

import com.harwexworld.dao.DAO;
import com.harwexworld.jdbc.IConnector;
import com.harwexworld.model.User;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAO implements DAO<User, Integer> {
    private final IConnector connector;

    public UserDAO(IConnector connector, String connectionUrl) {
        this.connector = connector;
        this.connector.createConnection(connectionUrl);
    }

    @Override
    public boolean create(User model) {
        boolean result = false;

        try (PreparedStatement statement = connector.getConnection().prepareStatement(SqlViewUserDAO.INSERT.QUERY)) {
            FillStatement(statement, model);
            result = statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            connector.closeConnection();
        }
        return result;
    }

    @Override
    public User readByKey(Integer id) {
        var user = new User();

        try (PreparedStatement statement = connector.getConnection().prepareStatement(SqlViewUserDAO.GET.QUERY)) {
            statement.setInt(1, id);
            var resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user.setId(id);
                user.setFirstName(resultSet.getString("FirstName"));
                user.setFirstName(resultSet.getString("LastName"));
                user.setFirstName(resultSet.getString("Address"));
                user.setFirstName(resultSet.getString("Passport"));
                user.setLogin(resultSet.getString("Login"));
                user.setPassword(resultSet.getString("Password"));
                user.setRole(new User.Role(resultSet.getInt("RoleId"), resultSet.getString("RoleName")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            connector.closeConnection();
        }
        return user;
    }

    @Override
    public boolean update(User model) {
        boolean result = false;

        try (PreparedStatement statement = connector.getConnection().prepareStatement(SqlViewUserDAO.UPDATE.QUERY)) {
            FillStatement(statement, model);
            result = statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            connector.closeConnection();
        }
        return result;
    }

    @Override
    public boolean delete(User model) {
        boolean result = false;

        try (PreparedStatement statement = connector.getConnection().prepareStatement(SqlViewUserDAO.DELETE.QUERY)) {
            statement.setInt(1, model.getId());
            result = statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            connector.closeConnection();
        }
        return result;
    }

    private void FillStatement(PreparedStatement statement, User model) throws SQLException {
        statement.setString(1, model.getFirstName());
        statement.setString(2, model.getLastName());
        statement.setString(3, model.getAddress());
        statement.setString(4, model.getPassport());
        statement.setInt(5, model.getRole().getId());
        statement.setString(6, model.getLogin());
        statement.setString(7, model.getPassword());
    }

    enum SqlViewUserDAO {
        GET("SELECT " +
                "u.Id, u.FirstName, u.LastName, r.Id as RoleId, r.Name as RoleName, u.Login, u.Password " +
                "FROM USER_ACCOUNT AS u INNER JOIN USER_ACCOUNT_TYPE AS r ON u.AccountType = r.Id " +
                "WHERE u.Id = (?)"),

        INSERT("INSERT" +
                " INTO USER_ACCOUNT " +
                "(FirstName, LastName, Address, Passport, AccountType, Login, Password)" +
                " VALUES " +
                "((?), (?), (?), (?), (?), (?), (?))"),

        DELETE("DELETE " +
                "FROM USER_ACCOUNT " +
                "WHERE Id = (?)"),

        UPDATE("UPDATE USER_ACCOUNT " +
                "SET FirstName = (?), LastName = (?), Address = (?), Passport = (?), " +
                    "AccountType = (?), Login = (?), Password = (?)" +
                "WHERE Id = (?)");

        String QUERY;

        SqlViewUserDAO(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}