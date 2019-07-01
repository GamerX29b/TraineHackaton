package com.example.TraineeHackathon.BaseClass;

import javax.persistence.*;

@Entity
@Table(name ="CAR")
public class CarBase {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "MODEL")
    private String model;

    @Column(name = "horsepower")
    private int horsepower;

    @Column(name = "ownerId")
    private Long ownerId;

    public CarBase (Long id, String model, Integer horsepower, Long ownerId){
        this.id = id;
        this.model = model;
        this.horsepower = horsepower;
        this.ownerId = ownerId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(int horsepower) {
        this.horsepower = horsepower;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public CarBase(){

    }
}
