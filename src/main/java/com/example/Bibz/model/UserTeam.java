package com.example.Bibz.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_team")
public class UserTeam  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userteam_id")
    private Long id;

    @Column(name = "user_id")
    private Long User_id;

    @Column(name = "team_id")
    private Long Team_id;


    @Column(name = "date_creat")
    private LocalDate dateCrea;
}
