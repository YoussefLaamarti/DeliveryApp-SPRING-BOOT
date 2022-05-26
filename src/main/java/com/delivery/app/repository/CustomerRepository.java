package com.delivery.app.repository;

import com.delivery.app.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {


    @Query("SELECT p FROM Person p WHERE p.email = ?1")
    public List<Customer> findByEmail(String email);
}
