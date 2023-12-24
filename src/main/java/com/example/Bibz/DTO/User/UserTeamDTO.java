package com.example.Bibz.DTO.User;

import com.example.Bibz.model.Team;
import com.example.Bibz.model.UserTeam;
import com.example.Bibz.model.user;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter@Setter
public class UserTeamDTO {
    private Long id;
    private Long user_id;
    private Long team_id;
    private LocalDate dateCrea;

    public UserTeamDTO(Long id, Long user_id, Long team_id, LocalDate dateCrea) {
        this.id = id;
        this.user_id = user_id;
        this.team_id = team_id;
        this.dateCrea = dateCrea;
    }

    public UserTeamDTO(){}
}
