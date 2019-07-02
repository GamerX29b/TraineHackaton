package com.example.TraineeHackathon.BaseClass;

import com.example.TraineeHackathon.Classes.Person;
import com.example.TraineeHackathon.Classes.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
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

    public List carsThisPerson (Long idPerson){

        CarBase carBase = new CarBase();
        carBase.setOwnerId(idPerson);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("id")
                .withIgnorePaths("model")
                .withIgnorePaths("horsepower");

        Example<CarBase> example = Example.of(carBase, matcher);

        List carList = carRepository.findAll(example);



        return carList;
    }

    public void carSave(Long id, String model, Integer horsepower, Long ownerId) {

        VendorBase vendorBase = new VendorBase();
        ModelBase modelBase = new ModelBase();

        vendorBase.setVendor("RollsRoys");
        List<VendorBase> vendorList = new ArrayList<>();

        vendorList.add(vendorBase);

        modelBase.setModel("Comet");
        modelBase.setVendorBases(vendorList);

        List<ModelBase> modelList = new ArrayList<>();
        modelList.add(modelBase);

        carRepository.save(new CarBase(id, modelList, horsepower, ownerId));
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

    public Statistics returnStatistics (){
        Statistics statistics = new Statistics();
        CarBase carBase = new CarBase();
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("id")
                .withIgnorePaths("horsepower")
                .withIgnorePaths("ownerId");


        Example<CarBase> unicleVendor = Example.of(carBase, matcher);

        List unicleModel = carRepository.findAll(unicleVendor);


        statistics.setPersoncount(personRepository.count());
        statistics.setCarcount(carRepository.count());


        return statistics;
    }
}
