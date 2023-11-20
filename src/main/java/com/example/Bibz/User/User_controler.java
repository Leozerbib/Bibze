package com.example.Bibz.User;

import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/rest/user/api")
public class User_controler {
    public static final Logger LOGGER = LoggerFactory.getLogger(User_controler.class);

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * Ajoute un nouveau client dans la base de donnée H2. Si le client existe déjà, on retourne un code indiquant que la création n'a pas abouti.
     * @param userDTORequest
     * @return
     */
    @PostMapping("/addUser")
    public ResponseEntity<UserDTO> createNewUser(@RequestBody UserDTO userDTORequest){
        user ExistingUser = userService.findUserByUsername(userDTORequest.getUsername());
        if (ExistingUser != null){
            return new ResponseEntity<UserDTO>(HttpStatusCode.valueOf(409));
        }
        user userRequest = mapUserDTOToUser(userDTORequest);
        userRequest.setDate_crea(LocalDate.now());
        user userResponse = userService.saveUser(userRequest);
        if (userResponse != null){
            UserDTO userDTO = mapUserToUserDTO(userResponse);
            return new ResponseEntity<UserDTO>(userDTO,HttpStatusCode.valueOf(201));
        }
        return new ResponseEntity<UserDTO>(HttpStatusCode.valueOf(304));
    }


    /**
     * Met à jour les données d'un client dans la base de donnée H2. Si le client n'est pas retrouvé, on retourne un code indiquant que la mise à jour n'a pas abouti.
     * @param userDTORequest
     * @return
     */
    @PutMapping("/updateUser")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTORequest){
        if (!userService.checkIfIdexists(userDTORequest.getUser_id())){
            return new ResponseEntity<>(HttpStatusCode.valueOf(404));
        }
        user userRequest = mapUserDTOToUser(userDTORequest);
        user userResponse = userService.updateUser(userRequest);
        if (userResponse != null ){
            UserDTO userDTO = mapUserToUserDTO(userResponse);
            return new ResponseEntity<UserDTO>(userDTO,HttpStatusCode.valueOf(200));
        }
        return new ResponseEntity<UserDTO>(HttpStatusCode.valueOf(304));
    }



    /**
     * Supprime un client dans la base de donnée H2. Si le client n'est pas retrouvé, on retourne le Statut HTTP NO_CONTENT.
     * @param user_id
     * @return
     */
    @DeleteMapping("/deleteUser/{user_id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer user_id){
        userService.deleteUser(user_id);
        return new ResponseEntity<String>(HttpStatusCode.valueOf(204));
    }



    /**
     * Retourne le client ayant l'adresse email passé en paramètre.
     * @param username
     * @return
     */
    @GetMapping("/searchByUsername")
    public ResponseEntity<UserDTO> searchUserByUsername(@RequestParam("username") String username) {
        //, UriComponentsBuilder uriComponentBuilder
        user user = userService.findUserByUsername(username);
        if (user != null) {
            UserDTO userDTO = mapUserToUserDTO(user);
            return new ResponseEntity<UserDTO>(userDTO, HttpStatusCode.valueOf(200));
        }
        return new ResponseEntity<UserDTO>(HttpStatusCode.valueOf(304));
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
        user user = mapper.map(UserDTO, user.class);
        return user;
    }

}
