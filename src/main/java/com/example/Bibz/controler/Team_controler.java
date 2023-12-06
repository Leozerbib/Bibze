package com.example.Bibz.controler;

import com.example.Bibz.service.implementation.TeamServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/Bibz/Team")
@RequiredArgsConstructor
public class Team_controler {

    @Autowired
    private TeamServiceImpl teamService;



}
