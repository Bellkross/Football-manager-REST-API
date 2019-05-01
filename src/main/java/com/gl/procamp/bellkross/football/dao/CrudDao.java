package com.gl.procamp.bellkross.football.dao;

import java.util.List;

public interface CrudDao<Entity, Id> {

    List<Entity> findAll();

    Entity findById(Id id);

    Entity save(Entity entity);

    void deleteById(Id id);

    void delete(Entity entity);

}
