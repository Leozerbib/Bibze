package com.example.Bibz.service;

import com.example.Bibz.DTO.TeamDto;
import com.example.Bibz.model.Team;
import com.example.Bibz.model.user;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.List;

public interface TeamService {
    public ResponseEntity<TeamDto> saveTeam(TeamDto Team);
    public Team updateTeam(Team team);
    public void deleteTeam(Long id);
    public Team findByIdAndPassword(Long id, String password);
    public Team findByNameAndPassword(String name, String password);
    public Team findByName(String name);
    public boolean checkIfIdexists(Long id);
    public Long findIdByName(String name);
}
