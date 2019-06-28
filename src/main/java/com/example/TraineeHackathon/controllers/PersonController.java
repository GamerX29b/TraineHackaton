package com.example.TraineeHackathon.controllers;


import com.example.TraineeHackathon.BaseClass.PersonBase;
import com.example.TraineeHackathon.BaseClass.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.TraineeHackathon.Classes.JsonResponse;
import com.example.TraineeHackathon.Classes.Person;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class PersonController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    PersonRepository repository;

    @RequestMapping(value = "/person.html", method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> person (
            @RequestBody String json
    ) {
        Gson gson = new GsonBuilder()
                .setDateFormat("dd.MM.yyyy").create();
        Person person = gson.fromJson(json,Person.class);

        logger.info("Inserting -> {}", repository.save(new PersonBase(person.getId(), person.getName(), person.getBirthdate())));


        System.out.println(person.getId());
        System.out.println(person.getName());
        System.out.println(person.getBirthdate());

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ResponseBody
    public JsonResponse addPerson(@RequestBody Person person){
        return new JsonResponse("OK","");
    }

}
