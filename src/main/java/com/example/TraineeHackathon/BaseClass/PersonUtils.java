package com.example.TraineeHackathon.BaseClass;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.Date;

public class PersonUtils {


    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    PersonRepository repository;

    public void personSave (Long id, String name, Date birthdate){

        logger.info("Inserting -> {}", repository.save(new PersonBase(id, name, birthdate)));
    }



}
