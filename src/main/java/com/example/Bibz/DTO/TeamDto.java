package com.example.Bibz.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter@Setter
public class TeamDto {
    private Long id;
    private String name;
    private int nbr_user;
    private LocalDate date_crea;
    private String pwd;

    public TeamDto(Long id, String name, int nbr_user, LocalDate date_crea, String pwd) {
        this.id = id;
        this.name = name;
        this.nbr_user = nbr_user;
        this.date_crea = date_crea;
        this.pwd = pwd;
    }

    public TeamDto(){}
}
