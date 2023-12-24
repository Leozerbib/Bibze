package com.example.Bibz.DTO.Team;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class USerTeamRequestDto {
    private Long idteam;
    private String username;
    private String passwords;
}
