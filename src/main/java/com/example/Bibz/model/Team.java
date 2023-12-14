package com.example.Bibz.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "team_id")
    private Long id;

    @Column(name = "name",nullable = false,unique = true)
    private String name;

    @Column(name = "nbr_user")
    private int nbr_user;

    @Column(name = "date_crea")
    private LocalDate date_crea;

    @Column(name = "password",nullable = false)
    private String pwd;

    @ManyToMany
    @JoinTable(name = "user_team",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<user> users;
}
