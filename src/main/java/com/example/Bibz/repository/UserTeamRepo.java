package com.example.Bibz.repository;

import com.example.Bibz.DTO.UserReturnDto;
import com.example.Bibz.model.Team;
import com.example.Bibz.model.UserTeam;
import com.example.Bibz.model.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Repository
public interface UserTeamRepo extends JpaRepository<UserTeam,Long> {
    @Query(value = "select  user.Id ,user.last_co,user.username from UserTeam ut left join fetch ut.User_id u where ut.Team_id = :teamID",
            nativeQuery  = false)
    public Set<user> findByTeam_id(Long teamID);


}
