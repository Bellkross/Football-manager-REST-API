package com.gl.procamp.bellkross.football.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Team {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "captain_id")
    private Player captain;
    @Setter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "team", fetch = FetchType.EAGER)
    private Set<Player> players = new HashSet<>();

    public void addPlayer(Player player) {
        players.add(player);
        player.setTeam(this);
    }

    public void removePlayer(Player player) {
        players.remove(player);
        player.setTeam(null);
    }

}
