package com.example.Bibz.DTO.Team;

import com.example.Bibz.model.user;
import com.example.Bibz.service.UserTeamService;
import com.example.Bibz.service.implementation.TeamServiceImpl;
import com.example.Bibz.service.implementation.UserteamServiceImpl;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Set;

@Getter@Setter

public class TeamDto {

    private Long id;
    private String name;
    private int nbr_user;
    private LocalDate date_crea;
    private Set<user> UserTeam;

    public TeamDto(Long id, String name, int nbr_user, LocalDate date_crea) {
        this.id = id;
        this.name = name;
        this.nbr_user = nbr_user;
        this.date_crea = date_crea;

    }



    public TeamDto(){}
}
