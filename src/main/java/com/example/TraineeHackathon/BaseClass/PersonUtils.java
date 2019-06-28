package com.example.TraineeHackathon.BaseClass;

import org.springframework.beans.factory.annotation.Autowired;


import java.util.Date;

public class PersonUtils {
    @Autowired
    PersonRepository personRepository;

    public void personSave (Long id, String name, Date birthdate){

        personRepository.save(new PersonBase(id, name, birthdate));
    }

}
