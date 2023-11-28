package com.example.Bibz;

import com.example.Bibz.DTO.UserDTO;
import com.example.Bibz.controler.User_controler;
import com.example.Bibz.model.user;
import com.example.Bibz.repository.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Optional;


@SpringBootApplication
public class BibzApplication {
	public static void main(String[] args) {

		SpringApplication.run(BibzApplication.class, args);

	}

	@Bean
	CommandLineRunner run(UserRepo userDao){

		return args -> {

		};
	}

}
