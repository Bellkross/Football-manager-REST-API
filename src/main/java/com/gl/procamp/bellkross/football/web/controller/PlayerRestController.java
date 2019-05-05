package com.gl.procamp.bellkross.football.web.controller;

import com.gl.procamp.bellkross.football.dao.PlayerRepository;
import com.gl.procamp.bellkross.football.dto.PlayerDto;
import com.gl.procamp.bellkross.football.model.Player;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.gl.procamp.bellkross.football.dto.PlayerDto.fromPlayer;
import static com.gl.procamp.bellkross.football.dto.PlayerDto.toPlayer;
import static com.gl.procamp.bellkross.football.web.controller.ErrorMessages.PLAYER_NOT_FOUND_EXCEPTION;
import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

@RestController
@RequestMapping("/players")
public class PlayerRestController {

    private final PlayerRepository playerDao;

    public PlayerRestController(PlayerRepository playerDao) {
        this.playerDao = playerDao;
    }

    @RequestMapping(method = RequestMethod.GET)
    List<PlayerDto> getPlayers() {
        return playerDao.findAll().stream().map(PlayerDto::fromPlayer).collect(Collectors.toList());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    PlayerDto getPlayer(@PathVariable Integer id) {
        requireNonNull(id);
        Player player = playerDao.findById(id)
                .orElseThrow((Supplier<RuntimeException>) () ->
                        new EntityNotFoundException(format(PLAYER_NOT_FOUND_EXCEPTION, id)));
        return fromPlayer(player);
    }

    @RequestMapping(method = RequestMethod.POST)
    PlayerDto postPlayer(@RequestBody PlayerDto player) {
        requireNonNull(player);
        return fromPlayer(playerDao.save(toPlayer(player)));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    PlayerDto putPlayer(@PathVariable Integer id, @RequestBody PlayerDto player) {
        requireNonNull(id);
        requireNonNull(player);
        requireNonNull(player.getId());
        if (!id.equals(player.getId())) {
            throw new IllegalArgumentException(format("Incorrect player id = %d", id));
        }
        return fromPlayer(playerDao.save(toPlayer(player)));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    void deletePlayer(@PathVariable Integer id) {
        requireNonNull(id);
        playerDao.deleteById(id);
    }

}