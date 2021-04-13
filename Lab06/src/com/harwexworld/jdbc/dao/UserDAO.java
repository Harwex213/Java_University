package com.harwexworld.jdbc.dao;

import com.harwexworld.dao.DAO;
import com.harwexworld.model.User;

public class UserDAO implements DAO<User, String> {
    @Override
    public boolean create(User model) {
        return false;
    }

    @Override
    public User readByKey(String s) {
        return null;
    }

    @Override
    public boolean update(User model) {
        return false;
    }

    @Override
    public boolean delete(User model) {
        return false;
    }

    enum MsSqlUser {
        GET("SELECT u.id, u.login, u.password, r.id AS rol_id, r.role FROM users AS u LEFT JOIN roles AS r ON u.role = r.id WHERE u.login = (?)"),
        INSERT("INSERT INTO users (id, login, password, role) VALUES (DEFAULT, (?), (?), (?)) RETURNING id"),
        DELETE("DELETE FROM users WHERE id = (?) AND login = (?) AND password = (?) RETURNING id"),
        UPDATE("UPDATE users SET password = (?) WHERE id = (?) RETURNING id");

        String QUERY;

        MsSqlUser(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}