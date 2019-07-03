package com.example.TraineeHackathon.BaseClass;


import javax.persistence.*;

@Entity
@Table(name ="vendor")
public class VendorBase {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "vendorName")
    private String vendorName;

    @ManyToOne
    private ModelBase modelBase;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

}
