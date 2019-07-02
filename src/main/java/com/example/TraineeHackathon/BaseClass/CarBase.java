package com.example.TraineeHackathon.BaseClass;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="CAR")
public class CarBase {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "horsepower")
    private int horsepower;

    @Column(name = "ownerId")
    private Long ownerId;

    @Column(name = "modelId")
    private Long modelId;

    @OneToMany(
            cascade = CascadeType.ALL)
    private List<ModelBase> modelBases = new ArrayList<>();

    public CarBase (Long id, Long model, Integer horsepower, Long ownerId){
        this.id = id;
        this.modelId = model;
        this.horsepower = horsepower;
        this.ownerId = ownerId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getModel() {
        return modelId;
    }

    public void setModel(Long model) {
        this.modelId = model;
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
 //----------------------------------------------------------------------------------------
    @Entity
    @Table(name ="model")
    public class ModelBase {

        @Id
        @GeneratedValue
        @Column(name = "id")
        private long id;

        @ManyToOne
        @JoinColumn(name = "modelId", nullable = false)
        private CarBase carBase;

        @Column(name = "model")
        private String model;

        @Column(name = "vendorId")
        private long vendorId;

        @OneToMany(
             cascade = CascadeType.ALL)
        private List<VendorBase> vendorBases = new ArrayList<>();

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

     public long getVendorId() {
         return vendorId;
     }

     public void setVendorId(long vendorId) {
         this.vendorId = vendorId;
     }
 }
    //------------------------------------------------------------------------
    @Entity
    @Table(name ="vendor")
    public class VendorBase {

        @Id
        @Column(name = "id")
        private long id;

        @Column(name = "vendor")
        private String vendor;

        @Column(name = "idModel")
        private long idModel;

        @ManyToOne
        @JoinColumn(name = "vendorId", nullable = false)
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

        public long getIdModel() {
            return idModel;
        }

        public void setIdModel(long idModel) {
            this.idModel = idModel;
        }
    }
}
