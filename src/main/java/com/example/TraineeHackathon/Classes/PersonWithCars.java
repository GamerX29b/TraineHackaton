package com.example.TraineeHackathon.Classes;

import java.lang.reflect.Array;
import java.util.Date;
import java.util.List;

public class PersonWithCars {
    Long id;
    String name;
    Date birthdate;
    List Cars;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public List getCars() {
        return Cars;
    }

    public void setCars(List cars) {
        Cars = cars;
    }

    public PersonWithCars() {
    }
}
