package com.example.Bibz.DTO.Team;

import com.example.Bibz.model.user;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeamPlusUserDto {
    private String name;
    private int nbr_user;
    private Set<user> users;
}
