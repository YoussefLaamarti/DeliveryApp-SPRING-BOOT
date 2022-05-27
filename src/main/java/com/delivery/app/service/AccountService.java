package com.delivery.app.service;

import com.delivery.app.model.Person;
import org.springframework.stereotype.Component;

@Component
public interface AccountService {

    Person loadUserByUsername(String username);
}
