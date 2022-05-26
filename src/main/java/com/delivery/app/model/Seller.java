package com.delivery.app.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Collection;
import java.util.Date;

@Entity(name = "Seller")
@DiscriminatorValue("Seller")
public class Seller extends Person{

    public Seller() {
    }

    public Seller(Long id, String username, String password, String first_name, String last_name, String email, String phone_number) {
        super(id, username, password, first_name, last_name, email, phone_number);
    }
}
