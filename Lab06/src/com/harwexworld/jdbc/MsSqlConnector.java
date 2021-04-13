package com.harwexworld.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MsSqlConnector implements IConnector {
    private static final String driverString = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static Connection connection;
    private String connectionUrl;

    static {
        try {
            Class.forName(driverString);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createConnection(String connectionUrl) {
        this.connectionUrl = connectionUrl;
    }

    @Override
    public Connection getConnection() {
        try {
            connection = DriverManager.getConnection(connectionUrl);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
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
