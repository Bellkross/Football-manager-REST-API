package com.gl.procamp.bellkross.football.dao;

import com.gl.procamp.bellkross.football.model.Team;

import java.util.Collection;
import java.util.Optional;

public interface CrudDao<Entity, Id> {

    Collection<Entity> findAll();
    Optional<Entity> findById(Id id);
    Optional<Entity> save(Entity entity);
    void deleteById(Id id);
    void delete(Entity entity);

}
