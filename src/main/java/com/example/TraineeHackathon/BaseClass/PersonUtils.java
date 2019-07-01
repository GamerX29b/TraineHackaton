package com.example.TraineeHackathon.BaseClass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Component
public class PersonUtils {
    @Autowired
    PersonRepository personRepository;

    public void personSave(Long id, String name, Date birthdate) {

        personRepository.save(new PersonBase(id, name, birthdate));
    }

    public boolean idValidate(Long id){

        Optional nullTest = personRepository.findById(id);

        if(nullTest.equals(Optional.empty())){
        return false;
    } else {
        return true;
        }
    }

}
