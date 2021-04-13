package com.harwexworld.jdbc;

import java.sql.Connection;

public interface IConnector {
    void createConnection(String connectionUrl);
    Connection getConnection();
    void closeConnection();
}