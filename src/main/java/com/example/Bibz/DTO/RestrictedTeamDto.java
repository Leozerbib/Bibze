package com.example.Bibz.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
@Getter@Setter
public class RestrictedTeamDto {
    private Long id;
    private String name;


    public RestrictedTeamDto(Long id, String name) {
        this.id = id;
        this.name = name;

    }

    public RestrictedTeamDto(){}
}
