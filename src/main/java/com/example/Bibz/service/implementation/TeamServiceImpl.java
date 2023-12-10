package com.example.Bibz.service.implementation;

import com.example.Bibz.DTO.LoginTeamDto;
import com.example.Bibz.DTO.RestrictedTeamDto;
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
    public ResponseEntity<RestrictedTeamDto> saveTeam(LoginTeamDto Team) {
        if (findByName(Team.getName()) !=null ){
            return new ResponseEntity<RestrictedTeamDto>(HttpStatus.CONFLICT);
        }
        Team teamRequest = mapLoginTeamDtoToTeam(Team);
        teamRequest.setDate_crea(LocalDate.now());
        System.out.println(teamRequest.getName());
        if (teamRepo.save(teamRequest) != null){
            RestrictedTeamDto restrictedTeamDto = mapTeamToRestctedTeamDto(teamRequest);
            return new ResponseEntity<RestrictedTeamDto>(restrictedTeamDto,HttpStatus.CREATED);
        }
        return new ResponseEntity<RestrictedTeamDto>(HttpStatus.NOT_MODIFIED);
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
    public TeamDto findByName(String name) {
        TeamDto teamDto = mapTeamToTeamDTO(teamRepo.findTeamByName(name));
        return teamDto;
    }

    @Override
    public boolean checkIfIdexists(Long id) {
        if(teamRepo.findTeamById(id) != null){
            return true;
        }
        return false;
    }

    @Override
    public Long findIdByName(String name) {
        return teamRepo.findIdByName(name);
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
    private Team mapRestrictedTeamDtoToTeam(RestrictedTeamDto restrictedTeamDto){
        ModelMapper mapper = new ModelMapper();
        Team team = new Team(1L,restrictedTeamDto.getName(), 0, null,null);
        return team;
    }

    private RestrictedTeamDto mapTeamToRestctedTeamDto(Team team){
        ModelMapper mapper = new ModelMapper();
        RestrictedTeamDto restrictedTeamDto = mapper.map(team,RestrictedTeamDto.class);
        return restrictedTeamDto;
    }
    private LoginTeamDto mapTeamToLoginTeamDto(Team team){
        ModelMapper mapper = new ModelMapper();
        LoginTeamDto loginTeamDto = mapper.map(team,LoginTeamDto.class);
        return loginTeamDto;
    }

    private Team mapLoginTeamDtoToTeam(LoginTeamDto loginTeamDto){
        ModelMapper mapper = new ModelMapper();
        Team team = new Team(1L, loginTeamDto.getName(),0, null, loginTeamDto.getPwd());
        return team;
    }
}
