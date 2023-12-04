package com.example.Bibz.service.implementation;

import com.example.Bibz.DTO.TeamDto;
import com.example.Bibz.model.Team;
import com.example.Bibz.model.user;
import com.example.Bibz.repository.TeamRepo;
import com.example.Bibz.service.TeamService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service("teamService")
@Transactional
@RequiredArgsConstructor
@Slf4j
public class TeamServiceImpl implements TeamService {

    @Autowired
    private final TeamRepo teamRepo;

    @Override
    public ResponseEntity<TeamDto> saveTeam(TeamDto Team) {
        if (findByName(Team.getName()) !=null ){
            return new ResponseEntity<TeamDto>(HttpStatus.CONFLICT);
        }
        Team teamRequest = mapTeamDTOToTeam(Team);
        teamRequest.setDate_crea(LocalDate.now());
        System.out.println(teamRequest.getName());
        Team teamResponse = teamRepo.save(teamRequest);
        if (teamRequest != null){
            TeamDto teamDTO = mapTeamToTeamDTO(teamResponse);
            return new ResponseEntity<TeamDto>(teamDTO,HttpStatus.CREATED);
        }
        return new ResponseEntity<TeamDto>(HttpStatus.NOT_MODIFIED);
    }

    @Override
    public Team updateTeam(Team team) {
        return teamRepo.save(team);
    }

    @Override
    public void deleteTeam(Long id) {
        teamRepo.deleteById(id);
    }

    @Override
    public Team findByIdAndPassword(Long id, String password) {
        return teamRepo.findTeamByIdAndPwd(id,password);
    }

    @Override
    public Team findByNameAndPassword(String name, String password) {
        return teamRepo.findTeamByNameAndPwd(name,password);
    }

    @Override
    public Team findByName(String name) {
        return teamRepo.findTeamByName(name);
    }

    @Override
    public boolean checkIfIdexists(Long id) {
        return false;
    }

    private TeamDto mapTeamToTeamDTO(Team team){
        ModelMapper mapper = new ModelMapper();
        TeamDto TeamDTO = mapper.map(team,TeamDto.class);
        return TeamDTO;
    }

    private Team mapTeamDTOToTeam(TeamDto teamDTO){
        ModelMapper mapper = new ModelMapper();
        Team team = new Team(1L,teamDTO.getName(), teamDTO.getNbr_user(), teamDTO.getDate_crea(),teamDTO.getPwd());
        return team;
    }
}
