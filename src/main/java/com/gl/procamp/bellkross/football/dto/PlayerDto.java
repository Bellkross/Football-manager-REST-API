package com.gl.procamp.bellkross.football.dto;

import com.gl.procamp.bellkross.football.model.Player;
import com.gl.procamp.bellkross.football.model.Position;
import lombok.*;

import java.util.Date;

import static java.util.Objects.nonNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PlayerDto {

    @EqualsAndHashCode.Include
    private Integer id;
    private String firstName;
    private String lastName;
    private Position position;
    private Date birthday;
    private Integer teamId;

    public PlayerDto(Player player) {
        this.id = player.getId();
        this.firstName = player.getFirstName();
        this.lastName = player.getLastName();
        this.position = player.getPosition();
        this.birthday = player.getBirthday();
        if (nonNull(player.getTeam())) {
            this.teamId = player.getTeam().getId();
        }
    }

    public static PlayerDto fromPlayer(final Player player) {
        return new PlayerDto(player);
    }

    public static Player toPlayer(PlayerDto playerDto) {
        Player player = new Player();
        player.setId(playerDto.getId());
        player.setFirstName(playerDto.getFirstName());
        player.setLastName(playerDto.getLastName());
        player.setBirthday(playerDto.getBirthday());
        player.setPosition(playerDto.getPosition());
        return player;
    }

}
