package com.gl.procamp.bellkross.football.dao;

import com.gl.procamp.bellkross.football.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository(value = "playerDao")
@Transactional
public interface PlayerRepository extends JpaRepository<Player, Integer> {

}
