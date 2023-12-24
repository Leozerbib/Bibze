package com.example.Bibz.DTO.User;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter@Setter
public class UserReturnDto {
    private int Id;
    private LocalDate Last_co;
    private String username;
    private String icon;


    public UserReturnDto(int user_id, LocalDate Last_co, String username,String icon) {
        this.Id = user_id;
        this.Last_co = Last_co;
        this.username = username;
        this.icon= icon;
    }
    public UserReturnDto(){
    }

    @Override
    public String toString() {
        return "UserReturnDto{" +
                "user_id=" + Id +
                ", Last_co=" + Last_co +
                ", username='" + username + '\'' +
                '}';
    }
}
