package com.delivery.app.repository;


import com.delivery.app.model.Delivery_man;
import com.delivery.app.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryManRepository extends JpaRepository<Delivery_man,Long> {
    public Person findByUsername(String username);
}
