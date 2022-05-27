package com.delivery.app.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity(name = "Delivery_man")
@DiscriminatorValue("Delivery_man")
public class Delivery_man extends Person {

    @Column
    private String CIN;
    private String Delivery_vehicule;

    @OneToMany(mappedBy = "delivery_man",
            cascade = CascadeType.ALL)

    private Collection<Box> pack;

    public Delivery_man() {
    }

    public Delivery_man(Long id, String username, String password, String first_name, String last_name, String email, String phone_number, String CIN, String delivery_vehicule, Collection<Box> pack,ROLE role) {
        super(id, username, password, first_name, last_name, email, phone_number,role);
        this.CIN = CIN;
        Delivery_vehicule = delivery_vehicule;
        this.pack = pack;
    }

    public String getCIN() {
        return CIN;
    }

    public void setCIN(String CIN) {
        this.CIN = CIN;
    }

    public Collection<Box> getPack() {
        return pack;
    }

    public void setPack(Collection<Box> pack) {
        this.pack = pack;
    }

    public String getDelivery_vehicule() {
        return Delivery_vehicule;
    }

    public void setDelivery_vehicule(String delivery_vehicule) {
        this.Delivery_vehicule = delivery_vehicule;
    }
}
