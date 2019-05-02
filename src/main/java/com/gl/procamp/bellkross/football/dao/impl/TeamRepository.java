package com.gl.procamp.bellkross.football.dao.impl;

import com.gl.procamp.bellkross.football.dao.TeamDao;
import com.gl.procamp.bellkross.football.model.Team;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

import static java.lang.String.format;
import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;

@Repository(value = "teamDao")
@Transactional
public class TeamRepository implements TeamDao {

    private final String TEAM_NOT_FOUND_EXCEPTION = "Team with id = %d not found";

    @PersistenceContext
    private EntityManager entityManager;

    public List<Team> findAll() {
        return entityManager.createQuery("select t from Team t", Team.class).getResultList();
    }

    public Team findById(Integer id) {
        requireNonNull(id);
        Team team = entityManager.find(Team.class, id);
        if (isNull(team)) {
            throw new EntityNotFoundException(format(TEAM_NOT_FOUND_EXCEPTION, id));
        }
        return team;
    }

    public Team save(Team team) {
        requireNonNull(team);
        if (isNull(team.getId())) {
            return saveNonExistingEntity(team);
        } else {
            return updateExistingEntity(team);
        }
    }

    private Team saveNonExistingEntity(Team team) {
        entityManager.persist(team);
        return team;
    }

    private Team updateExistingEntity(Team team) {
        requiredInDatabase(team.getId());
        return entityManager.merge(team);
    }

    private void requiredInDatabase(Integer id) {
        requireNonNull(id);
        if (isNull(entityManager.find(Team.class, id))) {
            throw new EntityNotFoundException(format(TEAM_NOT_FOUND_EXCEPTION, id));
        }
    }

    public void deleteById(Integer id) {
        requiredInDatabase(id);
        entityManager.remove(id);
    }

    public void delete(Team team) {
        requireNonNull(team);
        deleteById(team.getId());
    }

}
