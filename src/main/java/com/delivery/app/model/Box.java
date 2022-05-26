package com.delivery.app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
public class Box {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String package_name;

    private String code = UUID.randomUUID().toString();;
    private STATE status = STATE.NOT_YET_DELIVERED;


    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "deliveryman_id")
    @JsonBackReference(value="delivery_man")
    private Delivery_man delivery_man;

    public Box() {
    }

    public Box(String package_name, String code, STATE status,  Customer customer, Delivery_man delivery_man) {
        this.package_name = package_name;
        this.code = code;
        this.status = status;
        this.customer = customer;
        this.delivery_man = delivery_man;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public STATE getStatus() {
        return status;
    }

    public void setStatus(STATE status) {
        this.status = status;
    }



    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    public Delivery_man getDelivery_man() {
        return delivery_man;
    }

    public void setDelivery_man(Delivery_man delivery_man) {
        this.delivery_man = delivery_man;
    }
}