package com.example.Bibz.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
}
