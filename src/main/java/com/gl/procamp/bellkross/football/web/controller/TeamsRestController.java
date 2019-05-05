package com.gl.procamp.bellkross.football.web.controller;

import com.gl.procamp.bellkross.football.dao.PlayerRepository;
import com.gl.procamp.bellkross.football.dao.TeamRepository;
import com.gl.procamp.bellkross.football.dto.PlayerDto;
import com.gl.procamp.bellkross.football.dto.TeamDto;
import com.gl.procamp.bellkross.football.model.Player;
import com.gl.procamp.bellkross.football.model.Team;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.gl.procamp.bellkross.football.dto.PlayerDto.fromPlayer;
import static com.gl.procamp.bellkross.football.dto.TeamDto.fromTeam;
import static com.gl.procamp.bellkross.football.web.controller.ErrorMessages.PLAYER_NOT_FOUND_EXCEPTION;
import static com.gl.procamp.bellkross.football.web.controller.ErrorMessages.TEAM_NOT_FOUND_EXCEPTION;
import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

@RestController
@RequestMapping("/teams")
public class TeamsRestController {

    private final PlayerRepository playerDao;
    private final TeamRepository teamDao;

    public TeamsRestController(PlayerRepository playerDao, TeamRepository teamDao) {
        this.playerDao = playerDao;
        this.teamDao = teamDao;
    }

    @RequestMapping(method = RequestMethod.GET)
    List<TeamDto> getAllTeams() {
        return teamDao.findAll().stream().map(TeamDto::fromTeam).collect(Collectors.toList());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    TeamDto getTeam(@PathVariable("id") Integer id) {
        requireNonNull(id);
        return fromTeam(getExistingTeamById(id));
    }

    private Team getExistingTeamById(Integer id) {
        return teamDao.findById(id)
                .orElseThrow((Supplier<RuntimeException>) () ->
                        new EntityNotFoundException(format(TEAM_NOT_FOUND_EXCEPTION, id)));
    }

    @RequestMapping(method = RequestMethod.POST)
    TeamDto postTeam(@RequestBody Team team) {
        requireNonNull(team);
        return fromTeam(teamDao.save(team));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    TeamDto putTeam(@PathVariable("id") Integer id, @RequestBody Team team) {
        requireNonNull(id);
        requireNonNull(team);
        requireNonNull(team.getId());
        if (!id.equals(team.getId())) {
            throw new IllegalArgumentException(format("Incorrect team id = %d", id));
        }
        return fromTeam(teamDao.save(team));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    void deleteTeam(@PathVariable("id") Integer id) {
        requireNonNull(id);
        teamDao.deleteById(id);
    }

    @RequestMapping(value = "/{id}/players", method = RequestMethod.GET)
    Set<PlayerDto> getPlayers(@PathVariable("id") Integer id) {
        requireNonNull(id);
        return getExistingTeamById(id).getPlayers().stream()
                .map(PlayerDto::fromPlayer).collect(Collectors.toSet());
    }

    @RequestMapping(value = "/{id}/captain", method = RequestMethod.GET)
    PlayerDto getCaptain(@PathVariable("id") Integer id) {
        requireNonNull(id);
        return fromPlayer(getExistingTeamById(id).getCaptain());
    }


    @RequestMapping(value = "/{id}/player", method = RequestMethod.POST)
    PlayerDto postPlayerToATeam(@PathVariable("id") Integer id, @RequestBody Player player) {
        requireNonNull(id);
        requireNonNull(player);
        Team team = getExistingTeamById(id);
        team.addPlayer(player);
        teamDao.save(team);
        playerDao.save(player);
        return fromPlayer(player);
    }

    @RequestMapping(value = "/{team_id}/captain/{player_id}", method = RequestMethod.PUT)
    PlayerDto assignACaptain(@PathVariable("team_id") Integer teamId, @PathVariable("player_id") Integer playerId) {
        requireNonNull(teamId);
        requireNonNull(playerId);
        Team team = getExistingTeamById(teamId);
        Player player = playerDao.findById(playerId)
                .orElseThrow((Supplier<RuntimeException>) () ->
                        new EntityNotFoundException(format(PLAYER_NOT_FOUND_EXCEPTION, playerId)));
        team.addPlayer(player);
        team.setCaptain(player);
        teamDao.save(team);
        playerDao.save(player);
        return fromPlayer(player);
    }

}
