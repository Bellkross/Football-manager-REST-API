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
    private Integer id;
    @Column(nullable = false)
    private String name;
    @OneToOne(optional = false)
    private Player captain;
    @Setter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "team")
    private Set<Player> players = new HashSet<Player>();

}
