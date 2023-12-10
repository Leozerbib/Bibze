package com.example.Bibz.controler;

import com.example.Bibz.DTO.LoginTeamDto;
import com.example.Bibz.DTO.RestrictedTeamDto;
import com.example.Bibz.DTO.TeamDto;
import com.example.Bibz.DTO.UserDTO;
import com.example.Bibz.model.Team;
import com.example.Bibz.model.user;
import com.example.Bibz.service.implementation.TeamServiceImpl;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@CrossOrigin
@RequestMapping("/Bibz/Team")
@RequiredArgsConstructor
public class Team_controler {

    @Autowired
    private TeamServiceImpl teamService;

    @PostMapping(path = "/create")
    public Long CreateTeam(@RequestBody LoginTeamDto teamDto){
        System.out.println(teamDto);
        RestrictedTeamDto restrictedTeamDto = teamService.saveTeam(teamDto).getBody();
        return Long.valueOf(restrictedTeamDto.getId());
    }

    @PutMapping(path = "/update")
    public ResponseEntity<TeamDto> updateTeam(TeamDto teamDto){
        if (teamService.checkIfIdexists(teamDto.getId())){
            return new ResponseEntity<TeamDto>(HttpStatus.NOT_FOUND);
        }
        if(teamService.updateTeam(mapTeamDTOToTeam(teamDto)) != null){
            return new ResponseEntity<TeamDto>(teamDto,HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<TeamDto>(HttpStatus.NOT_MODIFIED);
    }


    @DeleteMapping(path = "/delete/{team_id}")
    public ResponseEntity<TeamDto> deleteTeam(TeamDto teamDto){
        teamService.deleteTeam(teamDto.getId());
        return new ResponseEntity<>(HttpStatus.GONE);
    }


    @GetMapping(path = "/get/findTeamByName")
    public ResponseEntity<TeamDto> findTeamByName(TeamDto teamDto){

        if(teamService.findByName(teamDto.getName()) instanceof TeamDto ){
            return new ResponseEntity<TeamDto>(teamService.findByName(teamDto.getName()),HttpStatus.ACCEPTED);
        }
        else{
            return new ResponseEntity<TeamDto>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/")





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
