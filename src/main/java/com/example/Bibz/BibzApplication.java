package com.example.Bibz;

import com.example.Bibz.repository.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


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
