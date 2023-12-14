package com.example.Bibz.controler;

import com.example.Bibz.DTO.*;
import com.example.Bibz.model.Team;
import com.example.Bibz.model.UserTeam;
import com.example.Bibz.model.user;
import com.example.Bibz.repository.TeamRepo;
import com.example.Bibz.repository.UserTeamRepo;
import com.example.Bibz.service.implementation.TeamServiceImpl;
import com.example.Bibz.service.implementation.UserServiceImpl;
import com.example.Bibz.service.implementation.UserteamServiceImpl;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("/Bibz/Team")
@RequiredArgsConstructor
public class Team_controler {

    @Autowired
    private TeamServiceImpl teamService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private UserteamServiceImpl userteamService;
    @PostMapping(path = "/create")
    public ResponseEntity<RestrictedTeamDto> CreateTeam(@RequestBody CreateTeamDto teamDto){
        System.out.println(teamDto.getName());
        System.out.println("oui");
        ResponseEntity<RestrictedTeamDto> restrictedTeamDto = teamService.saveTeam(teamDto);
        return restrictedTeamDto;
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
    @PostMapping(path = "/addUser")
    public ResponseEntity<Long> addUser(@RequestBody USerTeamRequestDto uSerTeamRequestDto){
        System.out.println(uSerTeamRequestDto.getUsername());
        System.out.println(uSerTeamRequestDto.getIdteam());
        UserDTO userDTO = mapUserToUserDTO(userService.findByUsernameOrEmail(uSerTeamRequestDto.getUsername(), uSerTeamRequestDto.getUsername()));
        System.out.println(userDTO.getId());
        System.out.println(userDTO);
        if (userDTO !=null){
            if (uSerTeamRequestDto.getPasswords().equals(userDTO.getPasswords())){
                if(teamService.checkIfIdexists(uSerTeamRequestDto.getIdteam())){
                    System.out.println(userDTO.getId());
                    System.out.println("oui");
                    UserTeamDTO userTeam = new UserTeamDTO(0L, userDTO.getId(), uSerTeamRequestDto.getIdteam(), LocalDate.now());
                    System.out.println(userTeam.getUser_id());
                    userteamService.saveUserTeam(userTeam);
                    System.out.println("oui");
                    return new ResponseEntity<Long>(uSerTeamRequestDto.getIdteam(),HttpStatus.CREATED);
                }
                else {
                    return new ResponseEntity<Long>(HttpStatus.NOT_FOUND);
                }
            }
            else {
                return new ResponseEntity<Long>(HttpStatus.CONFLICT);
            }
        }
        else {
            return new ResponseEntity<Long>(HttpStatus.NOT_FOUND);
        }

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
    @GetMapping(path = "/get/findUserByTeam")
    public ResponseEntity<TeamPlusUserDto> findUserByTeam(@RequestBody RestrictedTeamDto restrictedTeamDto){
        if (teamService.checkIfIdexists(restrictedTeamDto.getId())){
            Set<user> users = userteamService.findUserByTeam(restrictedTeamDto.getId());
            TeamPlusUserDto teamPlusUserDto =new TeamPlusUserDto(restrictedTeamDto.getName(),users.size(),users);
            return new ResponseEntity<TeamPlusUserDto>(teamPlusUserDto,HttpStatus.FOUND);
        }
        return new ResponseEntity<TeamPlusUserDto>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/login")
    public ResponseEntity<RestrictedTeamDto> loginUser(@RequestBody LoginTeamDto LoginDTO){
        String msg = "";
        Long id = teamService.findIdByName(LoginDTO.getName());
        if (teamService.checkIfIdexists(id) == true){
            String passwordTest = LoginDTO.getPwd();
            String encodedPassword = teamService.findByIdAndPassword(id,passwordTest).getPwd();
            Boolean isPwdOk = passwordTest.equals(encodedPassword);
            if (isPwdOk){
                    System.out.println("Login Success");
                    ResponseEntity<RestrictedTeamDto> userResponseEntity=new ResponseEntity<RestrictedTeamDto>(mapTeamToRestrictedTeamDTO(teamService.findByIdAndPassword(id,passwordTest)), HttpStatusCode.valueOf(200));
                    System.out.println(userResponseEntity);
                    return userResponseEntity;

            }
            else{

                System.out.println("Password not valid");
                ResponseEntity<RestrictedTeamDto> userResponseEntity=new ResponseEntity<RestrictedTeamDto>(HttpStatusCode.valueOf(406));
                System.out.println(userResponseEntity);
                return userResponseEntity;
            }
        }
        else{

            System.out.println("Username or Email not valid");
            ResponseEntity<RestrictedTeamDto> userResponseEntity=new ResponseEntity<RestrictedTeamDto>(HttpStatusCode.valueOf(406));
            System.out.println(userResponseEntity);
            return userResponseEntity;
        }


    }







    private TeamDto mapTeamToTeamDTO(Team team){
        ModelMapper mapper = new ModelMapper();
        TeamDto TeamDTO = mapper.map(team,TeamDto.class);
        return TeamDTO;
    }

    private Team mapTeamDTOToTeam(TeamDto teamDTO){
        ModelMapper mapper = new ModelMapper();
        Team team = new Team(1L,teamDTO.getName(), teamDTO.getNbr_user(), teamDTO.getDate_crea(),teamDTO.getPwd(),null);
        return team;
    }

    private RestrictedTeamDto mapTeamToRestrictedTeamDTO(Team team){
        ModelMapper mapper = new ModelMapper();
        RestrictedTeamDto TeamDTO = mapper.map(team,RestrictedTeamDto.class);
        return TeamDTO;
    }

    private Team mapRestrictedTeamDTOToTeam(RestrictedTeamDto teamDTO){
        ModelMapper mapper = new ModelMapper();
        Team team = new Team(1L,teamDTO.getName(), 0,null,null,null);
        return team;
    }
    private UserDTO mapUserToUserDTO(user user) {
        System.out.println(user.getPasswords());
        ModelMapper mapper = new ModelMapper();
        UserDTO UserDTO = mapper.map(user, UserDTO.class);
        UserDTO.setId(UserDTO.getId());
        return UserDTO;
    }
    /**
     * Transforme un POJO CustomerDTO en une entity Customer
     *
     * @param UserDTO
     * @return
     */
    private user mapUserDTOToUser(UserDTO UserDTO) {
        ModelMapper mapper = new ModelMapper();
        user user = new user(1L, UserDTO.getNames(), UserDTO.getLastname(), UserDTO.getUsername(), UserDTO.getAge(), LocalDate.now(),LocalDate.now(), UserDTO.getEmail(), UserDTO.getPasswords(),null);
        return user;
    }

}
