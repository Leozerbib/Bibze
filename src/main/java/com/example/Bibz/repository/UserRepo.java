package com.example.Bibz.repository;

import com.example.Bibz.DTO.RestrictedUserDro;
import com.example.Bibz.DTO.UserDTO;
import com.example.Bibz.model.user;
import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.SqlResultSetMapping;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<user, Long> {
    public user findByAge(int age);
    public user findByUsername(String username);
    public user findByNames(String names);
    public user findByEmail(String email);
    public user findByIdAndPasswords(Long id, String passwords);
    public user findByUsernameOrEmail(String Log, String Logs);

}
