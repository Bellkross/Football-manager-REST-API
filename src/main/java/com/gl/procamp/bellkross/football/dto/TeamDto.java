package com.gl.procamp.bellkross.football.dto;

import com.gl.procamp.bellkross.football.model.Team;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToOne;

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
        this.captain = PlayerDto.fromPlayer(team.getCaptain());
    }

    public static TeamDto fromTeam(Team team) {
        return new TeamDto(team);
    }

}
