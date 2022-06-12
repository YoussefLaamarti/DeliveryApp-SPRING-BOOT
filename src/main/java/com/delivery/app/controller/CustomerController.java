package com.delivery.app.controller;

import com.delivery.app.model.*;
import com.delivery.app.repository.CustomerRepository;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.net.*;
import java.util.Random;

@RestController
@CrossOrigin("*")
public class CustomerController {


    @Autowired
    private CustomerRepository CustomerRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    //Get all Customers [SELLER]
    @GetMapping("/customers")
    @PostAuthorize("hasAuthority('SELLER')")
    public List<Customer> getAllCustomers() {

        return CustomerRepo.findAll();
    }


    //Create customer [SELLER]
    @PostMapping("/customer")
    @PostAuthorize("hasAuthority('SELLER')")
    public void createCustomer(@RequestBody Customer e) throws IOException {
        String RandomPwd = randomizer();
        String HashedPwd = passwordEncoder.encode(RandomPwd);
        e.setPassword(HashedPwd);
        String email = e.getEmail();
        String user = e.getUsername();
        this.emailsender(email,RandomPwd,user);
        e.setRole(ROLE.CUSTOMER);
        CustomerRepo.save(e);
    }
    //Find by Id [SELLER]
    @GetMapping("/customer/{id}")
    @PostAuthorize("hasAuthority('SELLER')")
    public Optional<Customer> getCustomerviaId(@PathVariable(value = "id") long custid){

        return CustomerRepo.findById(custid);
    }
    //Find By Email [SELLER]
    @GetMapping("/customer/c/{email}")
    @PostAuthorize("hasAuthority('SELLER')")
    public List<Customer> getCustomerViaEmail(@PathVariable(value = "email") String mail){

        return CustomerRepo.findByEmail(mail);


    }

    //Find only Box related to this Customer [CUSTOMER]
    @GetMapping("/customer/user/{username}")
    @PostAuthorize("hasAuthority('CUSTOMER')")
    public Collection<Box> pwdCustomer(@PathVariable(value = "username" ) String custuu) {

        Collection<Box> a = null ;
        Customer c = (Customer) CustomerRepo.findByUsername(custuu);

            a =  c.getPack();

        return a;
    }

    public void emailsender(String em, String rnd, String usr) throws IOException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        String template = "{\r\n    \"personalizations\": [\r\n        {\r\n            \"to\": [\r\n                {\r\n                    \"email\": \"%s\"\r\n                }\r\n            ],\r\n            \"subject\": \"DeliveryApp login\"\r\n        }\r\n    ],\r\n    \"from\": {\r\n        \"email\": \"DeliveryTeam@Support.com\"\r\n    },\r\n    \"content\": [\r\n        {\r\n            \"type\": \"text/html\",\r\n            \"value\": \"password : <h1> %s</h1> username : <h1> %s</h1>\"\r\n        }\r\n    ]\r\n}";

        com.squareup.okhttp.RequestBody body = com.squareup.okhttp.RequestBody.create(mediaType, String.format(template,em,rnd,usr));
        Request request = new Request.Builder()
                .url("https://rapidprod-sendgrid-v1.p.rapidapi.com/mail/send")
                .post(body)
                .addHeader("content-type", "application/json")
                .addHeader("X-RapidAPI-Host", "rapidprod-sendgrid-v1.p.rapidapi.com")
                .addHeader("X-RapidAPI-Key", "02113d22b9msh4634ed9d8a484eap174484jsn0f04a8410583")
                .build();

        Response response = client.newCall(request).execute();
        System.out.println(em);
    }

    public String randomizer(){
        String password = new Random().ints(10, 33, 122).collect(StringBuilder::new,
                        StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    return password;
    }



}
