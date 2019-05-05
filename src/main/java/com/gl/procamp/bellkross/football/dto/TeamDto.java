package com.gl.procamp.bellkross.football.dto;

import com.gl.procamp.bellkross.football.model.Team;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TeamDto {

    @EqualsAndHashCode.Include
    private Integer id;
    private String name;
    private Integer captainId;

    public TeamDto(Team team) {
        this.id = team.getId();
        this.name = team.getName();
        this.captainId = team.getCaptain().getId();
    }

    public static TeamDto fromTeam(Team team) {
        return new TeamDto(team);
    }

    public static Team toTeam(TeamDto teamDto) {
        Team team = new Team();
        team.setId(teamDto.getId());
        team.setName(teamDto.getName());
        return team;
    }

}
