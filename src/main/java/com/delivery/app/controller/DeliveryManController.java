package com.delivery.app.controller;

import com.delivery.app.model.Box;
import com.delivery.app.model.Customer;
import com.delivery.app.model.Delivery_man;
import com.delivery.app.repository.DeliveryManRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class DeliveryManController {
    @Autowired
    DeliveryManRepository delivRepo;


    //Find only Box related to this DeliveryMan [DELIVERYMAN]
    @GetMapping("/delivery/id/{id}")
    public Collection<Box> pwdDelivery(@PathVariable(value = "id") long deliverytid) {

        Collection<Box> a = null;
        Optional<Delivery_man> c = delivRepo.findById(deliverytid);
        if (c.isPresent()) {

            Delivery_man o = c.get();

            a = o.getPack();
        }
        return a;
    }


}