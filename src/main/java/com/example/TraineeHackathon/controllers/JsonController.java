package com.example.TraineeHackathon.controllers;


import com.example.TraineeHackathon.BaseClass.BaseUtils;
import com.example.TraineeHackathon.Classes.Person;
import com.example.TraineeHackathon.Classes.PersonWithCars;
import com.example.TraineeHackathon.Classes.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;


@RestController
public class JsonController {

    private final BaseUtils baseUtils;

    @Autowired
    public JsonController(BaseUtils baseUtils) {
        this.baseUtils = baseUtils;
    }

    @RequestMapping(value = "/personwithcars.html", method = RequestMethod.GET)
    public @ResponseBody
    Object json(@RequestParam final Long personid) {


        Person person = baseUtils.retunPerson(personid);

        if (person == null) {
            return ResponseEntity.notFound().build();
        }

        List list = baseUtils.carsThisPerson(personid);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        PersonWithCars personWithCars = new PersonWithCars();
        personWithCars.setId(person.getId());
        personWithCars.setBirthdate(dateFormat.format(person.getBirthdate()));
        personWithCars.setName(person.getName());
        personWithCars.setCars(list);

        return personWithCars;
    }

    @RequestMapping(value = "/statistics.html", method = RequestMethod.GET)
    public @ResponseBody
    Object json() {
        Statistics statistics = new Statistics();

        statistics = baseUtils.returnStatistics();

        return statistics;
    }
}
