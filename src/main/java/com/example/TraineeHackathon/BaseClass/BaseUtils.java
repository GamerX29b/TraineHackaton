package com.example.TraineeHackathon.BaseClass;

import com.example.TraineeHackathon.Classes.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.*;

@Component
public class BaseUtils {
    @Autowired
    PersonRepository personRepository;

    @Autowired
    CarRepository carRepository;

    public void personSave(Long id, String name, Date birthdate) {

        personRepository.save(new PersonBase(id, name, birthdate));
    }

    public boolean idValidate(Long id) {

        Optional nullTest = personRepository.findById(id);

        if (nullTest.equals(Optional.empty())) {
            return false;
        } else {
            return true;
        }
    }

    public void carSave(Long id, String model, Integer horsepower, Long ownerId) {
        carRepository.save(new CarBase(id, model, horsepower, ownerId));
    }

    public Person retunPerson(Long id) {
        Person person = new Person();
        PersonBase personBase;
        try {
            personBase = personRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            return null;
        }
        person.setId(personBase.getId());
        person.setBirthdate(personBase.getBirthdate());
        person.setName(personBase.getName());

        return person;
    }

}
