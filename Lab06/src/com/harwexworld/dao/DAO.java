package com.harwexworld.dao;

public interface DAO<Entity, Key>{
    void create(Entity model);
    Entity readByKey(Key key);
    void update(Entity model);
    void delete(Entity model);
}