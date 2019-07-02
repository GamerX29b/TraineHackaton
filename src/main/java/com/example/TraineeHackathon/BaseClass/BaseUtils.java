package com.example.TraineeHackathon.BaseClass;

import com.example.TraineeHackathon.Classes.Car;
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

    @Autowired
    VendorRepository vendorRepository;

    //Добавление персоны
    public void personSave(Long id, String name, Date birthdate) {

        personRepository.save(new PersonBase(id, name, birthdate));
    }

    //валидаторы существования ID машины
    public boolean idValidate(Long id) {

        Optional nullTest = personRepository.findById(id);

        if (nullTest.equals(Optional.empty())) {
            return false;
        } else {
            return true;
        }
    }

    //Получить информацию о машине персоны
    public List carsThisPerson(Long idPerson) {

        CarBase carBase = new CarBase();
        carBase.setOwnerId(idPerson);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("id")
                .withIgnorePaths("model")
                .withIgnorePaths("horsepower");

        Example<CarBase> example = Example.of(carBase, matcher);
        List<Car> carList = new ArrayList<>();

        List<CarBase> result = carRepository.findAll(example);

        for (int i = 0; i < result.size(); i++) {
            Car car = new Car();
            CarBase bufferCar = result.get(i);
            ModelBase bufferModel = bufferCar.getModelBase().get(0);
            VendorBase vendorBase = bufferModel.getVendorBases().get(0);
            car.setId(bufferCar.getId());
            car.setModel(vendorBase.getVendor() + "-" + bufferModel.getModel());
            car.setHorsepower(bufferCar.getHorsepower());
            car.setOwnerId(bufferCar.getOwnerId());
            carList.add(car);
        }
        return carList;
    }

    //Сохранение данных в базу
    public void carSave(Long id, String model, Integer horsepower, Long ownerId) {
        String vendor = model.split("-")[0];
        String modelName = model.substring(model.indexOf('-') + 1);
        VendorBase vendorBase = new VendorBase();
        ModelBase modelBase = new ModelBase();
        vendorBase.setVendor(vendor);
        List<VendorBase> vendorList = new ArrayList<>();
        vendorList.add(vendorBase);
        modelBase.setModel(modelName);
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

    //Вернуть статистику
    public Statistics returnStatistics() {
        Statistics statistics = new Statistics();
        VendorBase vendorBase = new VendorBase();
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("id");


        Example<VendorBase> unicleVendor = Example.of(vendorBase, matcher);


        statistics.setPersoncount(personRepository.count());
        statistics.setCarcount(carRepository.count());
     //   statistics.setUniclevendercount(vendorRepository.);

        return statistics;
    }
    public void clearAll(){
        carRepository.deleteAll();
        personRepository.deleteAll();
    }
}
