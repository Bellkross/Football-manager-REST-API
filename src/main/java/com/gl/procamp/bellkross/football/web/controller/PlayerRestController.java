package com.gl.procamp.bellkross.football.web.controller;

import com.gl.procamp.bellkross.football.dao.PlayerDao;
import com.gl.procamp.bellkross.football.model.Player;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Objects.requireNonNull;

@RestController
@RequestMapping("/players")
public class PlayerRestController {

    private final PlayerDao playerDao;

    public PlayerRestController(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    @GetMapping
    List<Player> getPlayers() {
        return playerDao.findAll();
    }

    @GetMapping("/{id}")
    Player getPlayer(@PathVariable Integer id) {
        requireNonNull(id);
        return playerDao.findById(id);
    }

    @PostMapping
    Player postPlayer(@RequestBody Player player) {
        requireNonNull(player);
        return playerDao.save(player);
    }

    @PutMapping("/{id}")
    Player putPlayer(@PathVariable Integer id, @RequestBody Player player) {
        requireNonNull(id);
        requireNonNull(player);
        requireNonNull(player.getId());
        if (!id.equals(player.getId())) {
            throw new IllegalArgumentException(String.format("Incorrect player id = %d", id));
        }
        return playerDao.save(player);
    }

    @DeleteMapping("/{id}")
    void deletePlayer(@PathVariable Integer id) {
        requireNonNull(id);
        playerDao.deleteById(id);
    }

}