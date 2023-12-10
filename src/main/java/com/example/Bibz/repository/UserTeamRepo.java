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
    public UserTeam findByTeam_id(Set<Team> team_id);
    public UserTeam findByUser_id(Set<user> user_id);
    public UserTeam findByUser_idAndTeam_id(Set<Team> team_id,Set<user> user_id);
    public UserTeam findByTeam_id(Long id);
    @Query("select  u.id ,u.names,u.username from UserTeam ut left join ut.user_id u where ut.team_id = :teamID")
    public List<UserReturnDto> findByUser_id(Long teamID);


}
