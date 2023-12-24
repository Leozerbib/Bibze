package com.example.Bibz.service;

import com.example.Bibz.DTO.Team.CreateTeamDto;
import com.example.Bibz.DTO.Team.RestrictedTeamDto;
import com.example.Bibz.DTO.Team.TeamDto;
import com.example.Bibz.model.Team;
import com.example.Bibz.model.user;
import org.springframework.http.ResponseEntity;

import java.util.Set;

public interface TeamService {
    public ResponseEntity<RestrictedTeamDto> saveTeam(CreateTeamDto Team);
    public Team updateTeam(Team team);
    public void deleteTeam(Long id);
    public Team findByIdAndPassword(Long id, String password);
    public Team findByNameAndPassword(String name, String password);
    public TeamDto findByName(String name);
    public boolean checkIfIdexists(Long id);
    public boolean checkIfNameExist(String name);
    public Team findTeamById(Long id);
    public Long findIdByName(String name);
    public Set<user> findUserByTeam(Long id);
}
