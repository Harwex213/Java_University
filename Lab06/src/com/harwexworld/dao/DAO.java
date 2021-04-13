package com.harwexworld.dao;

public interface DAO<Entity, Key>{
    boolean create(Entity model);
    Entity readByKey(Key key);
    boolean update(Entity model);
    boolean delete(Entity model);
}