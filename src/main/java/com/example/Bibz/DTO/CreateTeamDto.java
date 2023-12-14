package com.example.Bibz.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateTeamDto {
    private String name;
    private int nbr_user;
    private String pwd;
}
