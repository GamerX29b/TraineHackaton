package com.example.TraineeHackathon.BaseClass;


import javax.persistence.*;

@Entity
@Table(name ="vendor")
public class VendorBase {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "vendor")
    private String vendor;

    @ManyToOne
    private ModelBase modelBase;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

}
