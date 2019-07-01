package com.example.TraineeHackathon.controllers;


import com.example.TraineeHackathon.BaseClass.PersonUtils;
import com.example.TraineeHackathon.Classes.Car;
import com.example.TraineeHackathon.Classes.JsonResponse;
import com.example.TraineeHackathon.Classes.Person;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@Controller
public class GeneralController {

    private final PersonUtils personUtils;

    @Autowired
    public GeneralController(PersonUtils personUtils){
        this.personUtils = personUtils;
    }

    @RequestMapping(value = "/person.html", method = RequestMethod.POST)
    public ResponseEntity person(
            @RequestBody String json
    ) {
        Gson gson = new GsonBuilder()
                .setDateFormat("dd.MM.yyyy").create();

        Person person;
        try{
            person = gson.fromJson(json, Person.class);       //проверка на ошибки валидации
        } catch (JsonSyntaxException e) {
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }

        if (!(person.getBirthdate() != null && person.getName() != null))
        {
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }

        if (personUtils.idValidate(person.getId()))                          //проверка на то, что такого айдишника нет
        {
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST); //вернуть конкретную ошибку валидации?
        }
        Date date = new Date();
        if (!(person.getBirthdate().before(date)))                        //проверка на прошедшую дату
        {
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
        //Сюда добавить валидаторы

        personUtils.personSave(person.getId(), person.getName(), person.getBirthdate());

        return ResponseEntity.ok(HttpStatus.OK);

    }

    @RequestMapping(value = "/car.html", method = RequestMethod.POST)
    public ResponseEntity car(
            @RequestBody String json
    ) {
        Gson gson = new GsonBuilder()
                .setDateFormat("dd.MM.yyyy").create();
        Car car = gson.fromJson(json, Car.class);

        //Сюда добавить валидаторы

       // personUtils.personSave(person.getId(), person.getName(), person.getBirthdate());



        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ResponseBody
    public JsonResponse addPerson(@RequestBody Person person) {
        return new JsonResponse("OK", "");
    }

}
