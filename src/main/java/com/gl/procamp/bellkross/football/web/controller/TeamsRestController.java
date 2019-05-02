package com.gl.procamp.bellkross.football.web.controller;

import com.gl.procamp.bellkross.football.dao.PlayerDao;
import com.gl.procamp.bellkross.football.dao.TeamDao;
import com.gl.procamp.bellkross.football.model.Player;
import com.gl.procamp.bellkross.football.model.Team;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

import static java.util.Objects.requireNonNull;

// todo: add field checking for team and player entities

@RestController
@RequestMapping("/teams")
public class TeamsRestController {

    private final PlayerDao playerDao;
    private final TeamDao teamDao;

    public TeamsRestController(PlayerDao playerDao, TeamDao teamDao) {
        this.playerDao = playerDao;
        this.teamDao = teamDao;
    }

    @GetMapping("/{id}")
    Team getTeam(@PathVariable Integer id) {
        requireNonNull(id);
        return teamDao.findById(id);
    }

    @PostMapping
    Team postTeam(@RequestBody Team team) {
        requireNonNull(team);
        return teamDao.save(team);
    }

    @PutMapping("/{id}")
    Team putTeam(@PathVariable Integer id, @RequestBody Team team) {
        requireNonNull(id);
        requireNonNull(team);
        requireNonNull(team.getId());
        if (!id.equals(team.getId())) {
            throw new IllegalArgumentException(String.format("Incorrect team id = %d", id));
        }
        return teamDao.save(team);
    }

    @DeleteMapping("/{id}")
    void deleteTeam(@PathVariable Integer id) {
        requireNonNull(id);
        teamDao.deleteById(id);
    }

    @GetMapping
    List<Team> getAllTeams() {
        return teamDao.findAll();
    }

    @GetMapping("/{id}/players")
    Set<Player> getPlayers(@PathVariable Integer id) {
        requireNonNull(id);
        return teamDao.findById(id).getPlayers();
    }

    @GetMapping("/{id}/captain")
    Player getCaptain(@PathVariable Integer id) {
        requireNonNull(id);
        return teamDao.findById(id).getCaptain();
    }


    @PostMapping("/{id}/player")
    Player postPlayerToATeam(@PathVariable Integer id, @RequestBody Player player) {
        requireNonNull(id);
        requireNonNull(player);
        Team team = teamDao.findById(id);
        team.addPlayer(player);
        teamDao.save(team);
        playerDao.save(player);
        return player;
    }

    @PostMapping("/{team_id}/captain/{player_id}")
    Player assignACaptain(@PathVariable("team_id") Integer teamId, @PathVariable Integer playerId) {
        requireNonNull(teamId);
        requireNonNull(playerId);
        Team team = teamDao.findById(teamId);
        Player player = playerDao.findById(playerId);
        team.addPlayer(player);
        team.setCaptain(player);
        teamDao.save(team);
        playerDao.save(player);
        return player;
    }

}
