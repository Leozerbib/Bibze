package com.example.Bibz.service;

import com.example.Bibz.DTO.UserTeamDTO;
import com.example.Bibz.model.Team;
import com.example.Bibz.model.UserTeam;
import com.example.Bibz.model.user;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

public interface UserTeamService {
    public ResponseEntity<UserTeamDTO> saveUserTeam(UserTeamDTO UserTeamDTO);
    public UserTeam updateUserTeam(UserTeam userTeam);
    public void deleteUser(Long id);
    public Collection<user> findUserByTeam(Long id);
    public Collection<Team> findTeamByUser(Long id);

}
