package com.example.TraineeHackathon.controllers;


import com.example.TraineeHackathon.BaseClass.BaseUtils;
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
import java.util.GregorianCalendar;


@Controller
public class GeneralController {

    private final BaseUtils baseUtils;

    @Autowired
    public GeneralController(BaseUtils baseUtils) {
        this.baseUtils = baseUtils;
    }

    @RequestMapping(value = "/person.html", method = RequestMethod.POST)
    public ResponseEntity person(
            @RequestBody String json
    ) {
        Gson gson = new GsonBuilder()
                .setDateFormat("dd.MM.yyyy").create();

        Person person;
        try {
            person = gson.fromJson(json, Person.class);       //проверка на ошибки валидации
        } catch (JsonSyntaxException e) {
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }

        if (!(person.getBirthdate() != null && person.getName() != null)) {
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }

        if (baseUtils.idValidate(person.getId()))                          //проверка на то, что такого айдишника нет
        {
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST); //вернуть конкретную ошибку валидации?
        }
        Date date = new Date();
        if (!(person.getBirthdate().before(date)))                        //проверка на прошедшую дату
        {
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
        //Сюда добавить валидаторы

        baseUtils.personSave(person.getId(), person.getName(), person.getBirthdate());

        return ResponseEntity.ok(HttpStatus.OK);

    }

    @RequestMapping(value = "/car.html", method = RequestMethod.POST)
    public ResponseEntity car(
            @RequestBody String json
    ) {
        Gson gson = new GsonBuilder()
                .setDateFormat("dd.MM.yyyy").create();
        Car car;
        //проверка на ошибки валидации
        try {
            car = gson.fromJson(json, Car.class);
        } catch (JsonSyntaxException e) {
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
        //Проверка правильного написания модели
        String model = car.getModel();
        int defis = 0;
        for (int i = 0; i < model.length(); i++)
            if (model.charAt(i) == '-') {
                defis++;
                if (defis != 1) {
                    return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
                }
            }
        //У машины есть мотор
        if (!(car.getHorsepower() > 0)) {                                    //проверка на ЛС
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
        //проверка на наличие ID в базе
        Person person = baseUtils.retunPerson(car.getOwnerId());
        if (person == null) {
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
        //Угадайте сколько милисекунд в 18 годах или проверка на возраст
        GregorianCalendar thisCalendar = new GregorianCalendar();
        GregorianCalendar birthdatePerson = new GregorianCalendar();
        birthdatePerson.setTime(person.getBirthdate());
        Long milisecond = thisCalendar.getTimeInMillis() - birthdatePerson.getTimeInMillis();
        if (milisecond < 567648000000L) {
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
        baseUtils.carSave(car.getId(), car.getModel(), car.getHorsepower(), car.getOwnerId());
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @RequestMapping(value = "/clear.html")
    public Object clearAll()
        {
            baseUtils.clearAll();
            return ResponseEntity.ok(HttpStatus.OK);
    }



    @ResponseBody
    public JsonResponse addPerson(@RequestBody Person person) {
        return new JsonResponse("OK", "");
    }

}
