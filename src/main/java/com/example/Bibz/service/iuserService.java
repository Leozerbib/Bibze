package com.example.Bibz.service;

import com.example.Bibz.DTO.RestrictedUserDro;
import com.example.Bibz.DTO.UserDTO;
import com.example.Bibz.Response.LoginResponse;
import com.example.Bibz.model.user;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

import java.util.List;

public interface iuserService {
    public ResponseEntity<UserDTO> saveUser(UserDTO user);
    public user updateUser(user user);

    public void deleteUser(Long id);
    public RestrictedUserDro findByIdAndPassword(Long id, String passwords);
    public user findByUsernameOrEmail(String log, String logs);

    public user findUserByUsername(String username);
    public user findUserByNames(String names);
    public boolean checkIfIdexists(Long id);
    public List<user> getUserByAge(int age);
    public Collection<user> getAllbyAge(int age);
    public LoginResponse loginUser(user user);

}
