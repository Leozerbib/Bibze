package com.example.Bibz.service;

import com.example.Bibz.DTO.User.UserTeamDTO;
import com.example.Bibz.model.Team;
import com.example.Bibz.model.UserTeam;
import com.example.Bibz.model.user;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.Set;

public interface UserTeamService {
    public ResponseEntity<UserTeam> saveUserTeam(UserTeamDTO UserTeam);
    public UserTeam updateUserTeam(UserTeam userTeam);
    public void deleteUser(Long id);
    public Set<user> findUserByTeam(Long id);
    public Collection<Team> findTeamByUser(Long id);
    public boolean checkIfExist(Long id);

    public UserTeam checkIfExistDouble(Long id,Long idx);
}
