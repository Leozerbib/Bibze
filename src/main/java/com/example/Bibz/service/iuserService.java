package com.example.Bibz.service;

import com.example.Bibz.DTO.User.RestrictedUserDro;
import com.example.Bibz.DTO.User.UserDTO;
import com.example.Bibz.Response.LoginResponse;
import com.example.Bibz.model.user;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

import java.util.List;
import java.util.Optional;

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
    public Optional<user> findById(Long id);

}
