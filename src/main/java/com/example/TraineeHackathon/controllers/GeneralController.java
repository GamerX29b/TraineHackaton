package com.example.TraineeHackathon.controllers;


import com.example.TraineeHackathon.BaseClass.BaseUtils;
import com.example.TraineeHackathon.Classes.Car;
import com.example.TraineeHackathon.Classes.Person;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;


@Controller
public class GeneralController {

    private final BaseUtils baseUtils;

    @Autowired
    public GeneralController(BaseUtils baseUtils) {
        this.baseUtils = baseUtils;
    }

    @RequestMapping(value = "/person", method = RequestMethod.POST)
    public ResponseEntity person(
            @RequestBody String json
    ) {
        Gson gson = new GsonBuilder()
                .setDateFormat("dd.MM.yyyy").create();

        Person person = new Person();
        if (json != null) {
            int i = json.length();
            char[] characters = new char[10];
            json.getChars(i-12, i-2, characters,0);
            String wtf = String.valueOf(characters);

            SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
            df.setLenient(false);
           try { Date date = df.parse(wtf);
               System.out.println(date);
           }catch (ParseException e) {return ResponseEntity.badRequest().build();}


            if (json.charAt(i-7) != '.' || json.charAt(i-10) != '.'){
                return ResponseEntity.badRequest().build();
            }
        }

        try {
            person = gson.fromJson(json, Person.class);               //проверка на ошибки валидации
        } catch (JsonSyntaxException e) {
            return ResponseEntity.badRequest().build();
        }

        if (person.getBirthdate() == null || person.getName() == null || person.getId() == 0) {
            return ResponseEntity.badRequest().build();
        }

        if (baseUtils.idPersonValidate(person.getId()))                //проверка на то, что человек есть
        {
            return ResponseEntity.badRequest().build();
        }

        Date date = new Date();
        if (!(person.getBirthdate().before(date)))                     //проверка на прошедшую дату
        {
            return ResponseEntity.badRequest().build();
        }

        baseUtils.personSave(person.getId(), person.getName(), person.getBirthdate());

        return ResponseEntity.ok(HttpStatus.OK);

    }

    @RequestMapping(value = "/car", method = RequestMethod.POST)
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
            return ResponseEntity.badRequest().build();
        }
            if (car.getModel() == null){
                return ResponseEntity.badRequest().build();
            }
        if (car.getHorsepower() == null){
            return ResponseEntity.badRequest().build();
        }

        if (car.getModel().equals("")
                || car.getHorsepower() == 0
                || car.getId() == 0
                || car.getOwnerId() == 0) {
            return ResponseEntity.badRequest().build();
        }

        //проверка на наличие ID в базе
        Person person = baseUtils.retunPerson(car.getOwnerId());
        if (person == null) {
            return ResponseEntity.badRequest().build();
        }
        //проверка на то, что такого айдишника машины нет
        if (baseUtils.idCarValidate(car.getId()))
        {
            return ResponseEntity.badRequest().build();
        }
        //Проверка правильного написания модели
        if (car.getModel().charAt(0)== '-'){
                    return ResponseEntity.badRequest().build();
        }
        //У машины есть мотор
        if (!(car.getHorsepower() > 0)) {                                    //проверка на ЛС
            return ResponseEntity.badRequest().build();
        }

        //Угадайте сколько милисекунд в 18 годах или проверка на возраст
        GregorianCalendar thisCalendar = new GregorianCalendar();
        GregorianCalendar birthdatePerson = new GregorianCalendar();
        birthdatePerson.setTime(person.getBirthdate());
        Long milisecond = thisCalendar.getTimeInMillis() - birthdatePerson.getTimeInMillis();
        if (milisecond < 567648000000L) {
            return ResponseEntity.badRequest().build();
        }
        baseUtils.carSave(car.getId(), car.getModel(), car.getHorsepower(), car.getOwnerId());
        return ResponseEntity.ok().build();
    }
    @RequestMapping(value = "/clear")
    public ResponseEntity clearAll()
        {
            baseUtils.clearAll();
            return ResponseEntity.ok().build();
    }
}
