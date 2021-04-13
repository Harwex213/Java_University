package com.harwexworld.main;

import com.harwexworld.jdbc.*;
import com.harwexworld.jdbc.dao.*;
import com.harwexworld.model.*;

public class Main {
    public static void main(String[] args) {
        IConnector msSqlConnector = new MsSqlConnector();
        var connectionUrl = "jdbc:sqlserver://localhost;database=HARWEX_BANK;integratedSecurity=true;";
        var userDAO = new UserDAO(msSqlConnector, connectionUrl);

        var userIgor = new User();
        userIgor.setFirstName("GAGA");
        userIgor.setLastName("GAGSFA");
        userIgor.setAddress("Minsk, Vaneeva 10, 18");
        userIgor.setPassport("MG1234567");
        userIgor.setRole(new User.Role(1, "admin"));
        userIgor.setLogin("traveler");
        userIgor.setPassword("qwerty");
        userDAO.create(userIgor);
        var userOleg = userDAO.readByKey(14);
        userDAO.delete(userOleg);
        userDAO.create(userOleg);
    }
}
