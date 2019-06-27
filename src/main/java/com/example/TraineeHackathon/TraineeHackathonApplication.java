package com.example.TraineeHackathon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TraineeHackathonApplication {

	public static void main(String[] args) {
		SpringApplication.run(TraineeHackathonApplication.class, args);
	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringBootWebJspApplication.class);
	}

}
