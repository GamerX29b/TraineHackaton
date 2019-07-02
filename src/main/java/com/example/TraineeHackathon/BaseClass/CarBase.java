package com.example.TraineeHackathon.BaseClass;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CAR")
public class CarBase {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "horsepower")
    private int horsepower;

    @Column(name = "ownerId")
    private Long ownerId;


    @OneToMany(
            cascade = CascadeType.ALL)
    @JoinTable(name = "model")
    private List<ModelBase> modelBase;

    public List<ModelBase> getModelBase() {
        return modelBase;
    }

    public void setModelBase(List<ModelBase> modelBase) {
        this.modelBase = modelBase;
    }

    public CarBase(Long id, List modelBase, Integer horsepower, Long ownerId) {
        this.id = id;
        this.horsepower = horsepower;
        this.modelBase = modelBase;
        this.ownerId = ownerId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public CarBase() {

    }
}
