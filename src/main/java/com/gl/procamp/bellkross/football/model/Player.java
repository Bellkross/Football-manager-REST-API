package com.gl.procamp.bellkross.football.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Player {

    @Id
    @EqualsAndHashCode.Include
    private Integer id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Position position;
    @Column(nullable = false)
    private Date birthday;
    @ManyToOne
    private Team team;

}
