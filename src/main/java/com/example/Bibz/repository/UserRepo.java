package com.example.Bibz.repository;

import com.example.Bibz.model.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<user, Long> {
    public user findByAge(int age);
    public user findByUsername(String username);
    public user findByNames(String names);
    public user findByEmail(String email);
    public user findByIdAndPassword(Long id,String password);
    public user findByUsernameOrEmail(String Log,String Logs);

}
