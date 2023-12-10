package com.example.Bibz.controler;

import com.example.Bibz.DTO.TeamDto;
import com.example.Bibz.DTO.UserDTO;
import com.example.Bibz.DTO.UserReturnDto;
import com.example.Bibz.DTO.UserTeamDTO;
import com.example.Bibz.service.implementation.TeamServiceImpl;
import com.example.Bibz.service.implementation.UserteamServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping("/Bibze/UserTeam")
@RequiredArgsConstructor
@RestController
public class UserTeam_controler {

    @Autowired
    private UserteamServiceImpl userteamService;

    @Autowired
    private TeamServiceImpl teamService;

    @PostMapping(path = "/create_UserTeam")
    public Long create(UserTeamDTO userTeamDTO){
        System.out.println(userTeamDTO);
        userteamService.saveUserTeam(userTeamDTO);
        return userTeamDTO.getId();
    }

    @PutMapping(path = "/update_UserTeam")
    public ResponseEntity<UserTeamDTO> UpdateUserTeam(UserTeamDTO userTeamDTO){
        if (userteamService.checkIfExist(userTeamDTO.getId())){
            return new ResponseEntity<UserTeamDTO>(userTeamDTO, HttpStatus.FOUND);
        }
        return new ResponseEntity<UserTeamDTO>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(path = "/delete_UserTeam/{userteam_id}")
    public ResponseEntity<UserTeamDTO> Delete(Long id){
        userteamService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.GONE);
    }

    @GetMapping(path = "/get/findUserbyTeam")
    public ResponseEntity<List<UserReturnDto>> findUserByTeam(TeamDto teamDto){
        List<UserReturnDto> userDTOList = userteamService.findUserByTeam(teamDto.getId());
        return new ResponseEntity<List<UserReturnDto>>(userDTOList,HttpStatus.FOUND);
    }

}
