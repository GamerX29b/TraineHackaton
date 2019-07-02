package com.example.TraineeHackathon.BaseClass;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="model")
public class ModelBase {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @ManyToOne
    private CarBase carBase;

    @Column(name = "model")
    private String model;

    @OneToMany(
            cascade = CascadeType.ALL)
    private List<VendorBase> vendorBases = new ArrayList<>();


    public List<VendorBase> getVendorBases() {
        return vendorBases;
    }

    public void setVendorBases(List<VendorBase> vendorBases) {
        this.vendorBases = vendorBases;
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
}
