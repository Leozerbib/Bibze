package com.example.Bibz.service.implementation;

import com.example.Bibz.DTO.UserTeamDTO;
import com.example.Bibz.model.Team;
import com.example.Bibz.model.UserTeam;
import com.example.Bibz.model.user;
import com.example.Bibz.repository.UserTeamRepo;
import com.example.Bibz.service.UserTeamService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;

@Service("userTeamService")
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserteamServiceImpl implements UserTeamService {
    @Autowired
    private final UserTeamRepo userTeamRepo;
    @Override
    public ResponseEntity<UserTeamDTO> saveUserTeam(UserTeamDTO UserTeamDTO) {
        if (userTeamRepo.existsById(UserTeamDTO.getId())){
            return new ResponseEntity<UserTeamDTO>(HttpStatus.CONFLICT);
        }
        UserTeam UserTeamRequest = mapUserTeamDTOToUserTeam(UserTeamDTO);
        UserTeamRequest.setDateCrea(LocalDate.now());
        if (userTeamRepo.save(UserTeamRequest) != null){
            UserTeamDTO userTeamDTO = mapUserTeamToUserTeamDTO(UserTeamRequest);
            return new ResponseEntity<UserTeamDTO>(HttpStatus.CREATED);
        }
        return new ResponseEntity<UserTeamDTO>(HttpStatus.NOT_MODIFIED);
    }

    @Override
    public UserTeam updateUserTeam(UserTeam userTeam) {
        return userTeamRepo.save(userTeam);
    }

    @Override
    public void deleteUser(Long id) {
        userTeamRepo.deleteById(id);
    }

    @Override
    public Collection<user> findUserByTeam(Long id) {
        return (Collection<user>) ;
    }

    @Override
    public Collection<Team> findTeamByUser(Long id) {
        return null;
    }

    private UserTeamDTO mapUserTeamToUserTeamDTO(UserTeam userTeam){
        ModelMapper mapper = new ModelMapper();
        UserTeamDTO userTeamDTO = mapper.map(userTeam,UserTeamDTO.class);
        return userTeamDTO;
    }

    private UserTeam mapUserTeamDTOToUserTeam(UserTeamDTO userTeamDTO){
        ModelMapper mapper = new ModelMapper();
        UserTeam userTeam = new UserTeam(userTeamDTO.getId(),userTeamDTO.getUser_id(),userTeamDTO.getTeam_id(),userTeamDTO.getDateCrea());
        return userTeam;
    }
}
