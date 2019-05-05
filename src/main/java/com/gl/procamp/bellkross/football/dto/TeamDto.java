package com.gl.procamp.bellkross.football.dto;

import com.gl.procamp.bellkross.football.model.Team;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import static com.gl.procamp.bellkross.football.dto.PlayerDto.fromPlayer;
import static com.gl.procamp.bellkross.football.dto.PlayerDto.toPlayer;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TeamDto {

    @Id
    @EqualsAndHashCode.Include
    private Integer id;
    @Column(nullable = false)
    private String name;
    @OneToOne(optional = false)
    private PlayerDto captain;

    public TeamDto(Team team) {
        this.id = team.getId();
        this.name = team.getName();
        this.captain = fromPlayer(team.getCaptain());
    }

    public static TeamDto fromTeam(Team team) {
        return new TeamDto(team);
    }

    public static Team toTeam(TeamDto teamDto) {
        Team team = new Team();
        team.setId(teamDto.getId());
        team.setName(teamDto.getName());
        team.setCaptain(toPlayer(teamDto.getCaptain()));
        return team;
    }

}
