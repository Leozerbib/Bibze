package com.example.Bibz.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        user ExistingUser = userService.findUserByUsername(userDTORequest.getUsername())
    }
}
