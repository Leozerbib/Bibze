package com.example.Bibz.DTO;


import lombok.*;

import java.time.LocalDate;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private int user_id;
    private String names;
    private String lastname;
    private String username;
    private int age;
    private LocalDate date_crea;
    private LocalDate LastCo;
    private String email;
    private String passwords;

}
