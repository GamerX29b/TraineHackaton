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
    private int ownerId;

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

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }
}
