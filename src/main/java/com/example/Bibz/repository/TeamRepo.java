package com.example.Bibz.repository;

import com.example.Bibz.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepo extends JpaRepository<Team,Long> {
    public Team findTeamById(Long id);
    public Team findTeamByName(String name);
    public boolean existsByName(String name);
    public boolean existsByid(Long id);
    public Team findTeamByIdAndPwd(Long id,String pwd);

    @Query(value = "select t.team_id from Team t where t.name = :name",
            nativeQuery = true)
    public Long findIdByName(String name);

    public Team findTeamByNameAndPwd(String name,String pwd);

}
