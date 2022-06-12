package com.delivery.app.repository;

import com.delivery.app.model.Customer;
import com.delivery.app.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {



    public List<Customer> findByEmail(String email);

    public Person findByUsername(String username);
}
