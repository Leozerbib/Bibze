package com.example.Bibz.DTO.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDto {
    private String names;
    private String lastname;
    private String username;
    private int age;
    private String email;
    private String passwords;



}
