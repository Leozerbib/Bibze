package com.example.Bibz.DTO;


import lombok.*;

import java.time.LocalDate;

@Getter@Setter
public class UserDTO {
    private int user_id;
    private String names;
    private String lastname;
    private String username;
    private int age;
    private String email;
    private String passwords;
    public UserDTO(int user_id, String names, String lastname, String username, int age, String email, String passwords) {
        this.user_id = user_id;
        this.names = names;
        this.lastname = lastname;
        this.username = username;
        this.age = age;
        this.email = email;
        this.passwords = passwords;
    }
    public UserDTO(){}
}
