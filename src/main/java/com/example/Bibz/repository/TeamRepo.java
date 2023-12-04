package com.example.Bibz.repository;

import com.example.Bibz.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepo extends JpaRepository<Team,Long> {
    public Team findTeamById(Long id);
    public Team findTeamByName(String name);
    public Team findTeamByIdAndPwd(Long id,String pwd);
    public Team findTeamByNameAndPwd(String name,String pwd);

}
