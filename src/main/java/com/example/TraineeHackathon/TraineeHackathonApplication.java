package com.example.TraineeHackathon;

import com.example.TraineeHackathon.BaseClass.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class TraineeHackathonApplication {

    public static void main(String[] args) {
        SpringApplication.run(TraineeHackathonApplication.class, args);
    }

    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(TraineeHackathonApplication.class);
    }

}
