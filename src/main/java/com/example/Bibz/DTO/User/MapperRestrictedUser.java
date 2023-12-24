package com.example.Bibz.DTO.User;

import com.example.Bibz.model.user;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;

public class MapperRestrictedUser {
    public static RestrictedUserDro map(user user) {
        ModelMapper mapper = new ModelMapper();
        RestrictedUserDro UserDTO = mapper.map(user, RestrictedUserDro.class);
        return UserDTO;
    }
    /**
     * Transforme un POJO CustomerDTO en une entity Customer
     *
     * @param UserDTO
     * @return
     */
    public  user mapDto(RestrictedUserDro UserDTO) {
        ModelMapper mapper = new ModelMapper();
        user user = new user(1L, null, null, UserDTO.getUsername(), 0, LocalDate.now(),LocalDate.now(), null, null,null,null);
        return user;
    }
}
