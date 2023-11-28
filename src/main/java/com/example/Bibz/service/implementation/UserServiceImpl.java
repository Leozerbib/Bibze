package com.example.Bibz.service.implementation;

import com.example.Bibz.DTO.UserDTO;
import com.example.Bibz.Response.LoginResponse;
import com.example.Bibz.repository.UserRepo;
import com.example.Bibz.model.user;
import com.example.Bibz.service.iuserService;
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
import java.util.List;

@Service("userService")
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements iuserService {

    @Autowired
    private final UserRepo userRepo;
    @Override
    public ResponseEntity<UserDTO> saveUser(UserDTO user) {
        if (findUserByUsername(user.getUsername()) != null) {
            return new ResponseEntity<UserDTO>(HttpStatus.CONFLICT);
        }
        user customerRequest = mapUserDTOToUser(user);
        customerRequest.setDate_crea(LocalDate.now());
        customerRequest.setLast_co(LocalDate.now());
        System.out.println(customerRequest.getUsername());
        user customerResponse = userRepo.save(customerRequest);
        if (customerResponse != null) {
            UserDTO customerDTO = mapUserToUserDTO(customerResponse);
            return new ResponseEntity<UserDTO>(customerDTO, HttpStatus.CREATED);
        }
        return new ResponseEntity<UserDTO>(HttpStatus.NOT_MODIFIED);
    }

    @Override
    public user updateUser(user user) {
        log.info("user udpate : {}",user.getId()  );
        return userRepo.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        log.info("user delete : {}",id  );
        userRepo.deleteById(id);
    }

    @Override
    public user findByIdAndPassword(Long id, String password) {
        return userRepo.findByIdAndPassword(id, password);
    }

    @Override
    public user findByUsernameOrEmail(String log,String logs) {
        return userRepo.findByUsernameOrEmail(log,logs);
    }

    @Override
    public user findUserByUsername(String username) {
        log.info("username : {}",username );
        return userRepo.findByUsername(username);
    }

    @Override
    public user findUserByNames(String names) {

        return userRepo.findByNames(names);
    }

    @Override
    public boolean checkIfIdexists(Long id) {

        return false;
    }

    @Override
    public List<user> getUserByAge(int age) {

        return (List<user>) userRepo.findByAge(age);
    }

    @Override
    public Collection<user> getAllbyAge(int age){
        log.info("user by age : {}",age  );
        return (Collection<user>) userRepo.findByAge(age);
    }

    @Override
    public LoginResponse loginUser(user user) {
        return null;
    }
    /**
     * Transforme une entity Customer en un POJO CustomerDTO
     *
     * @param user
     * @return
     */
    private UserDTO mapUserToUserDTO(user user) {
        ModelMapper mapper = new ModelMapper();
        UserDTO UserDTO = mapper.map(user, UserDTO.class);
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
        user user = new user(1L, UserDTO.getNames(), UserDTO.getLastname(), UserDTO.getUsername(), UserDTO.getAge(), LocalDate.now(),LocalDate.now(), UserDTO.getEmail(), UserDTO.getPasswords());
        return user;
    }
}