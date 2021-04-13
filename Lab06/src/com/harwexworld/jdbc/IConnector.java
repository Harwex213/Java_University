package com.harwexworld.jdbc;

import java.sql.Connection;

public interface IConnector {
    Connection openConnection(String connectionUrl);
    void closeConnection();
}