package com.harwexworld.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MsSqlConnector implements IConnector {
    private static final String driverString = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static Connection connection;

    public static Connection getConnection() {
        return connection;
    }

    @Override
    public Connection openConnection(String connectionUrl) {
        try {
            Class.forName(driverString);
            connection = DriverManager.getConnection(connectionUrl);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
}
