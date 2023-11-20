package com.example.Bibz.User;

import java.util.List;

public interface iuserService {
    public user saveUser(user user);
    public user updateUser(user user);
    public void deleteUser(Integer id);
    public user findUserByUsername(String username);
    public user findUserByNames(String names);
    public boolean checkIfIdexists(Integer id);
    public List<user> getUserByAge(int age);

}
