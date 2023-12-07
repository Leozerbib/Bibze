package com.example.Bibz.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class UserReturnDto {
    private int user_id;
    private String names;
    private String username;


    public UserReturnDto(int user_id, String names, String username) {
        this.user_id = user_id;
        this.names = names;
        this.username = username;
    }
    public UserReturnDto(){
    }
}
