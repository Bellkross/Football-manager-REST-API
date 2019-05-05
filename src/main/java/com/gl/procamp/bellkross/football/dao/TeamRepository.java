package com.gl.procamp.bellkross.football.dao;

import com.gl.procamp.bellkross.football.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository(value = "teamDao")
@Transactional
public interface TeamRepository extends JpaRepository<Team, Integer> {

}
