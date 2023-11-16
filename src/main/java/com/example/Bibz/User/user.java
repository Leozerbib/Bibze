package com.example.Bibz.User;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table (name = "users")
@Getter@Setter
@Data
public class user {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String names;
    private String lastname;
    private String username;
    private int age;
    private Timestamp date_crea;
    private Timestamp last_co;
}
