package com.example.Bibz.User;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table (name = "users")
@Setter@Getter
public class user {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "names",nullable = false)
    private String names;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "username",nullable = false,unique = true)
    private String username;

    @Column(name = "age")
    private int age;

    @Column(name = "date_crea",nullable = false)
    private Timestamp date_crea;

    @Column(name = "last_co",nullable = false)
    private Timestamp last_co;


}
