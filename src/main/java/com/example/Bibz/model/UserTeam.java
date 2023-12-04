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
@Table(name = "user_team")
public class UserTeam {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userteam_id")
    private Long id;

    @Column(name = "user_id")
    @ManyToMany
    @JoinTable(name = "users",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<user> user_id;

    @Column(name = "team_id")
    @ManyToMany
    @JoinTable(name = "team",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id"))
    private Set<Team> team_id;

    @Column(name = "date_creat")
    private LocalDate dateCrea;
}
