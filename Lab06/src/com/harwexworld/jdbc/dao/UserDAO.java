package com.harwexworld.jdbc.dao;

import com.harwexworld.jdbc.IConnector;
import com.harwexworld.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class UserDAO extends HarwexBankDbDAO<User, Integer> {
    public UserDAO(IConnector connector, String connectionUrl) {
        super(connector, connectionUrl);
    }

    @Override
    protected User GetModel() {
        return new User();
    }

    @Override
    protected void GetInsertQuery(String insertIntoKey, String valuesKey) {
        var insertIntoValue = "USER_ACCOUNT (FirstName, LastName, Address, Passport, AccountType, Login, Password)";
        var valuesValue = "(?), (?), (?), (?), (?), (?), (?)";

        getQueriesParams().put(insertIntoKey, insertIntoValue);
        getQueriesParams().put(valuesKey, valuesValue);
    }

    @Override
    protected void GetSelectQuery(String selectKey, String fromKey) {
        var selectValue = "u.Id, u.FirstName, u.LastName, u.Address, u.Passport," +
                " u.Login, u.Password, r.Id as RoleId, r.Name as RoleName";
        var fromValue = "USER_ACCOUNT AS u INNER JOIN USER_ACCOUNT_TYPE AS r ON u.AccountType = r.Id";

        getQueriesParams().put(selectKey, selectValue);
        getQueriesParams().put(fromKey, fromValue);
    }

    @Override
    protected void GetUpdateQuery(String updateFromKey, String setValuesKey) {
        var updateFromValue = "USER_ACCOUNT";
        var setValue = "FirstName = (?), LastName = (?), Address = (?), Passport = (?)," +
                " AccountType = (?), Login = (?), Password = (?)";

        getQueriesParams().put(updateFromKey, updateFromValue);
        getQueriesParams().put(setValuesKey, setValue);
    }

    @Override
    protected void GetDeleteQuery(String deleteFromKey) {
        var updateFromValue = "USER_ACCOUNT";

        getQueriesParams().put(deleteFromKey, updateFromValue);
    }

    @Override
    protected void FillStatement(PreparedStatement statement, User model) {
        try {
            statement.setString(1, model.getFirstName());
            statement.setString(2, model.getLastName());
            statement.setString(3, model.getAddress());
            statement.setString(4, model.getPassport());
            statement.setInt(5, model.getRole().getId());
            statement.setString(6, model.getLogin());
            statement.setString(7, model.getPassword());
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    protected void FillModelFromResultSet(ResultSet resultSet, User model) {
        try {
            model.setId(resultSet.getInt("Id"));
            model.setFirstName(resultSet.getString("FirstName"));
            model.setLastName(resultSet.getString("LastName"));
            model.setAddress(resultSet.getString("Address"));
            model.setPassport(resultSet.getString("Passport"));
            model.setLogin(resultSet.getString("Login"));
            model.setPassword(resultSet.getString("Password"));
            model.setRole(new User.Role(resultSet.getInt("RoleId"), resultSet.getString("RoleName")));
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    protected void SetKey(PreparedStatement statement, User model, int positionKey) {
        try {
            statement.setInt(positionKey, model.getId());
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    enum SaveSql {
        INSERT("INSERT " +
                "INTO USER_ACCOUNT " +
                "(FirstName, LastName, Address, Passport, AccountType, Login, Password) " +
                "VALUES " +
                "((?), (?), (?), (?), (?), (?), (?))"),

        SELECT ("SELECT " +
                "u.FirstName, u.LastName, u.Address, u.Passport, u.Login, u.Password, " +
                "r.Id as RoleId, r.Name as RoleName " +
                "FROM USER_ACCOUNT AS u INNER JOIN USER_ACCOUNT_TYPE AS r ON u.AccountType = r.Id " +
                "WHERE u.Id = (?)"),

        DELETE("DELETE " +
                "FROM USER_ACCOUNT " +
                "WHERE Id = (?)"),

        UPDATE("UPDATE USER_ACCOUNT " +
                "SET FirstName = (?), LastName = (?), Address = (?), Passport = (?), " +
                "AccountType = (?), Login = (?), Password = (?) " +
                "WHERE Id = (?)");

        String QUERY;

        SaveSql(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}