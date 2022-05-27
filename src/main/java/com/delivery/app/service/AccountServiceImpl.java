package com.delivery.app.service;

import com.delivery.app.model.Person;
import com.delivery.app.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountServiceImpl implements AccountService{
    @Autowired
    PersonRepository pp;
    @Override
    public Person loadUserByUsername(String username) {
        return pp.findByUsername(username);

    }
}
