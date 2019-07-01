package com.example.TraineeHackathon.BaseClass;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name ="person")
public class PersonBase {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "birthdate")
    private Date birthdate;

    public PersonBase(long id, String name, Date birthdate) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public PersonBase() {

    }
}
