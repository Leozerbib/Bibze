package com.example.Bibz.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface User_Dao extends JpaRepository<user, Integer> {
    public user findByAge(int age);
    public user findByUsername(String username);
    public user findByNames(String names);
}
