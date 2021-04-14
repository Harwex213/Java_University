package com.harwexworld.main;

import com.harwexworld.jdbc.*;
import com.harwexworld.jdbc.dao.*;
import com.harwexworld.model.*;

import java.time.temporal.Temporal;

public class Main {
    public static void main(String[] args) {

        IConnector msSqlConnector = new MsSqlConnector();
        var connectionUrl = "jdbc:sqlserver://localhost;database=HARWEX_BANK;integratedSecurity=true;";
        var userDAO = new UserDAO(msSqlConnector, connectionUrl);

//        var user1 = new User();
//        user1.setFirstName("New Oleg");
//        user1.setLastName("Testing");
//        user1.setAddress("Minsk, Vaneeva 10, 18");
//        user1.setPassport("BH1234567");
//        user1.setRole(new User.Role(3, "client"));
//        user1.setLogin("Qwerty");
//        user1.setPassword("Haha");
//        userDAO.create(user1);
        var user2 = userDAO.readByKey(23);
        userDAO.delete(user2);
        userDAO.create(user2);
    }
}
