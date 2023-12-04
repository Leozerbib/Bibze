package com.example.Bibz.DTO;

import com.example.Bibz.model.Team;
import com.example.Bibz.model.UserTeam;
import com.example.Bibz.model.user;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter@Setter
public class UserTeamDTO {
    private Long id;
    private Set<user> user_id;
    private Set<Team> team_id;
    private LocalDate dateCrea;

    public UserTeamDTO(Long id, Set<user> user_id, Set<Team> team_id, LocalDate dateCrea) {
        this.id = id;
        this.user_id = user_id;
        this.team_id = team_id;
        this.dateCrea = dateCrea;
    }

    public UserTeamDTO(){}
}
