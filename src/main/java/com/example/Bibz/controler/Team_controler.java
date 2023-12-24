package com.example.Bibz.controler;

import com.example.Bibz.DTO.Team.*;
import com.example.Bibz.DTO.User.UserDTO;
import com.example.Bibz.DTO.User.UserReturnDto;
import com.example.Bibz.DTO.User.UserTeamDTO;
import com.example.Bibz.model.Team;
import com.example.Bibz.model.user;
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
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("/Bibz/Team")
@RequiredArgsConstructor
public class Team_controler {

    private final mapper mapper;
    @Autowired
    private TeamServiceImpl teamService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private UserteamServiceImpl userteamService;
    @Autowired
    public Team_controler(mapper mapper, TeamServiceImpl teamService, UserServiceImpl userService, UserteamServiceImpl userteamService) {
        this.mapper = mapper;
        this.teamService = teamService;
        this.userService = userService;
        this.userteamService = userteamService;
    }

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
    public ResponseEntity<UserReturnDto> addUser(@RequestBody USerTeamRequestDto uSerTeamRequestDto){
        System.out.println(uSerTeamRequestDto.getUsername());
        System.out.println(uSerTeamRequestDto.getIdteam());
        user user = userService.findByUsernameOrEmail(uSerTeamRequestDto.getUsername(), uSerTeamRequestDto.getUsername());
        System.out.println(user.getId());
        System.out.println(user);
        if (user !=null){
            if (uSerTeamRequestDto.getPasswords().equals(user.getPasswords())){
                if(teamService.checkIfIdexists(uSerTeamRequestDto.getIdteam())){
                    if (userteamService.checkIfExistDouble(user.getId(), uSerTeamRequestDto.getIdteam()) == null){
                        System.out.println(user.getId());
                        System.out.println("oui");
                        UserReturnDto userDTO = mapper.mapUserToUserDTOReturn(user);
                        UserTeamDTO userTeam = new UserTeamDTO(0L, user.getId(), uSerTeamRequestDto.getIdteam(), LocalDate.now());
                        System.out.println(userTeam.getUser_id());
                        userteamService.saveUserTeam(userTeam);
                        System.out.println("oui");
                        return new ResponseEntity<UserReturnDto>(userDTO,HttpStatus.CREATED);
                    }
                    else {
                        return new ResponseEntity<UserReturnDto>(HttpStatus.CONFLICT);
                    }
                }
                else {
                    return new ResponseEntity<UserReturnDto>(HttpStatus.NOT_FOUND);
                }
            }
            else {
                return new ResponseEntity<UserReturnDto>(HttpStatus.CONFLICT);
            }
        }
        else {
            return new ResponseEntity<UserReturnDto>(HttpStatus.NOT_FOUND);
        }

    }


    @DeleteMapping(path = "/delete/{team_id}")
    public ResponseEntity<TeamDto> deleteTeam(TeamDto teamDto){
        teamService.deleteTeam(teamDto.getId());
        return new ResponseEntity<>(HttpStatus.GONE);
    }


    @GetMapping(path = "/get/findTeamByName")
    public ResponseEntity<TeamDto> findTeamByName(String name){
        TeamDto teamDto= teamService.findByName(name);
        if(teamDto instanceof TeamDto ){
            System.out.println("non");
            teamDto.setUserTeam(userteamService.findUserByTeam(teamDto.getId()));
            System.out.println("oui");
            return new ResponseEntity<TeamDto>(teamDto,HttpStatus.ACCEPTED);
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

    @GetMapping(path = "/get/findTeam/{teamId}")
    public RestrictedTeamDto findTeam(@PathVariable Long teamId){
        RestrictedTeamDto restrictedTeamDto1 = mapTeamToRestrictedTeamDTO(teamService.findTeamById(teamId));
        System.out.println(restrictedTeamDto1);
        return restrictedTeamDto1;

    }

    @PostMapping(path = "/login")
    public ResponseEntity<RestrictedTeamDto> loginUser(@RequestBody LoginTeamDto LoginDTO){
        String msg = "";
        if (teamService.checkIfNameExist(LoginDTO.getName()) == true){
            String passwordTest = LoginDTO.getPwd();
            Team team = teamService.findByNameAndPassword(LoginDTO.getName(), LoginDTO.getPwd());
            Boolean isPwdOk = passwordTest.equals(team.getPwd());
            if (isPwdOk){
                    System.out.println("Login Success");
                    ResponseEntity<RestrictedTeamDto> userResponseEntity=new ResponseEntity<RestrictedTeamDto>(mapTeamToRestrictedTeamDTO(team), HttpStatusCode.valueOf(200));
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
        Team team = new Team(1L,teamDTO.getName(), teamDTO.getNbr_user(), teamDTO.getDate_crea(),null,teamDTO.getUserTeam());
        return team;
    }

    private RestrictedTeamDto mapTeamToRestrictedTeamDTO(Team team){
        ModelMapper mapper = new ModelMapper();
        System.out.println("non");
        RestrictedTeamDto TeamDTO =new RestrictedTeamDto(team.getId(), team.getName(), team.getNbr_user(), team.getDate_crea(),null);
        System.out.println(TeamDTO.getUsers());
        Set<user> user = userteamService.findUserByTeam(team.getId());
        System.out.println(user);
        Set<UserReturnDto> userReturnDtos = new HashSet<>();
        for ( com.example.Bibz.model.user i :  user){
            userReturnDtos.add(mapper.map(i, UserReturnDto.class));
        }
        TeamDTO.setUsers(userReturnDtos);
        System.out.println(TeamDTO.getUsers());
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
        user user = new user(1L, UserDTO.getNames(), UserDTO.getLastname(), UserDTO.getUsername(), UserDTO.getAge(), LocalDate.now(),LocalDate.now(), UserDTO.getEmail(), UserDTO.getPasswords(),null,null);
        return user;
    }

}
