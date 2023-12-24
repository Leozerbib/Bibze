package com.example.Bibz.DTO.Team;

import java.time.LocalDate;
import java.util.Set;
import com.example.Bibz.model.user;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class TeamWithUserDto {
    private int team_id;
    private Set<user> userTeam;
    private String name;
    private LocalDate Date_crea;
    public TeamWithUserDto(int team_id, Set<user> userTeam, String name, LocalDate date_crea) {
        this.team_id = team_id;
        this.userTeam = userTeam;
        this.name = name;
        Date_crea = date_crea;
    }

    public TeamWithUserDto(){

    }

}
