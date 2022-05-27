package com.delivery.app.repository;

import com.delivery.app.model.Box;
import com.delivery.app.model.Delivery_man;
import com.delivery.app.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Long> {
    public Person findByUsername(String username);
}
