package com.gl.procamp.bellkross.football.dao.impl;

import com.gl.procamp.bellkross.football.dao.PlayerDao;
import com.gl.procamp.bellkross.football.model.Player;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

import static java.lang.String.format;
import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;

@Repository(value = "playerDao")
@Transactional
public class PlayerRepository implements PlayerDao {

    private final String PLAYER_NOT_FOUND_EXCEPTION = "Player with id = %d not found";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Player> findAll() {
        return entityManager.createQuery("select p from Player p", Player.class).getResultList();
    }

    @Override
    public Player findById(Integer id) {
        requireNonNull(id);
        requiredInDatabase(id);
        return entityManager.find(Player.class, id);
    }

    @Override
    public Player save(Player player) {
        requireNonNull(player);
        if (isNull(player.getId())) {
            return saveNonExistingEntity(player);
        } else {
            return updateExistingEntity(player);
        }
    }

    private Player saveNonExistingEntity(Player player) {
        entityManager.persist(player);
        return player;
    }

    private Player updateExistingEntity(Player player) {
        requiredInDatabase(player.getId());
        return entityManager.merge(player);
    }

    private void requiredInDatabase(Integer id) {
        requireNonNull(id);
        if (isNull(entityManager.find(Player.class, id))) {
            throw new EntityNotFoundException(format(PLAYER_NOT_FOUND_EXCEPTION, id));
        }
    }

    @Override
    public void deleteById(Integer id) {
        requiredInDatabase(id);
        entityManager.remove(id);
    }

    @Override
    public void delete(Player player) {
        requireNonNull(player);
        deleteById(player.getId());
    }
}
