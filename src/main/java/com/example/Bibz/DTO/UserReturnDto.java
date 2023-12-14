package com.example.Bibz.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter@Setter
public class UserReturnDto {
    private int user_id;
    private LocalDate Last_co;
    private String username;


    public UserReturnDto(int user_id, LocalDate Last_co, String username) {
        this.user_id = user_id;
        this.Last_co = Last_co;
        this.username = username;
    }
    public UserReturnDto(){
    }
}
