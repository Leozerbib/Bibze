package com.example.Bibz.DTO.Team;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class LoginTeamDto {
    private String name;
    private String pwd;

    public LoginTeamDto(Long id, String name, int nbr_user, LocalDate date_crea, String pwd) {

        this.name = name;
        this.pwd = pwd;
    }

    public LoginTeamDto(){}
}
