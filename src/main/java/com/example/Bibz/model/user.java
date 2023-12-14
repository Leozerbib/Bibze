package com.example.Bibz.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "users")

public class user {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id")
    private Long Id;

    @Column(name = "names",nullable = false)
    private String names;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "username",nullable = false,unique = true)
    private String username;

    @Column(name = "age")
    private int age;

    @Column(name = "date_crea")
    private LocalDate date_crea;

    @Column(name = "last_co")
    private LocalDate last_co;

    @Column(name = "email",unique = true,nullable = false)
    private String email;

    @Column(name = "passwords",nullable = false)
    private String passwords;

    @ManyToMany(mappedBy = "users")
    private Set<Team> teams;
}
