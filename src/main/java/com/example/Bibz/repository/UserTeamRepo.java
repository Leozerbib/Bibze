package com.example.Bibz.repository;

import com.example.Bibz.model.UserTeam;
import com.example.Bibz.model.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserTeamRepo extends JpaRepository<UserTeam,Long> {
    @Query(value = "select  ut from Team t join t.users ut where t.id = :teamID",
            nativeQuery  = false)
    public Set<user> findByTeam_id(Long teamID);
    @Query(value = "select  ut from UserTeam ut where ut.Team_id=:Team_id and ut.User_id= :User_id",
            nativeQuery  = false)
    public UserTeam findByTeam_idAndUser_idEqualsAndTeam_idEquals(Long User_id, Long Team_id);
}
