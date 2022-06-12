package com.delivery.app.controller;

import com.delivery.app.model.*;
import com.delivery.app.repository.DeliveryManRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class DeliveryManController {
    @Autowired
    DeliveryManRepository delivRepo;


    //Find only Box ( in progress or not yet delivered )  related to this DeliveryMan [DELIVERYMAN]
    @GetMapping("/delivery/d/{username}")
    @PostAuthorize("hasAuthority('DELIVERY_MAN')")
    public Collection<Box> pwdDelivery(@PathVariable(value = "username") String deliverytusername) {

        Collection<Box> a = null;
        List<Box> progornot ;
        progornot = new ArrayList<>();
        Delivery_man c = (Delivery_man) delivRepo.findByUsername(deliverytusername);

            a = c.getPack();
        for (Box bx : a){
            if((bx.getStatus() == STATE.IN_PROGRESS) || (bx.getStatus() == STATE.NOT_YET_DELIVERED)){
                progornot.add(bx);
            }
        }

        return progornot;
    }
    //Find only Box related to this DeliveryMan ( DELIVERED ONLY )  [DELIVERYMAN]
    @GetMapping("/delivery/do/{username}")
    @PostAuthorize("hasAuthority('DELIVERY_MAN')")
    public Collection<Box> pwdDeliveryo(@PathVariable(value = "username") String deliverytusername) {

        Collection<Box> a = null;
        List<Box> delivered ;
        delivered = new ArrayList<>();
        Delivery_man c = (Delivery_man) delivRepo.findByUsername(deliverytusername);

        a = c.getPack();


        for (Box bx : a){
            if(bx.getStatus() == STATE.DELIVERED){
                delivered.add(bx);
            }
        }

        return delivered;
    }

    //Find By  Username [DeliveryMan]
    @GetMapping("/delviery/uuu/{username}")
    @PostAuthorize("hasAuthority('DELIVERY_MAN')")
    public Person getDeliveryManViaUsername(@PathVariable(value = "username") String uu){
        Person a ;
         a = delivRepo.findByUsername(uu);
         return a ;

    }


}