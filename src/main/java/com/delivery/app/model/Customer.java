package com.delivery.app.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.Date;

@Entity(name = "Customer")
@DiscriminatorValue("Customer")
public class Customer extends Person{

    @OneToMany(mappedBy = "customer",
            cascade = CascadeType.ALL)

    private Collection<Box> pack;
    public Customer() {
    }

    public Customer(Long id, String username, String password, String first_name, String last_name, String email, String phone_number, Collection<Box> pack,ROLE role) {
        super(id, username, password, first_name, last_name, email, phone_number,role);
        this.pack = pack;
    }


    public Collection<Box> getPack() {
        return pack;
    }

    public void setPack(Collection<Box> pack) {
        this.pack = pack;
    }
}
