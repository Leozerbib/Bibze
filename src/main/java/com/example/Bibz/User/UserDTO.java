package com.example.Bibz.User;


import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter@Setter
public class UserDTO implements Comparable<UserDTO>{
    private Integer user_id;
    private String names;
    private String lastname;
    private String username;
    private int age;
    private Timestamp last_co;
    private Timestamp date_crea;

    @Override
    public int compareTo(UserDTO o) {
        return this.username.compareToIgnoreCase(o.username);
    }
}
