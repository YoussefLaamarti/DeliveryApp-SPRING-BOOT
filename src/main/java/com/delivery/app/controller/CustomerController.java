package com.delivery.app.controller;

import com.delivery.app.model.Box;
import com.delivery.app.model.Customer;
import com.delivery.app.model.Delivery_man;
import com.delivery.app.model.Person;
import com.delivery.app.repository.CustomerRepository;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.springframework.beans.factory.annotation.Autowired;
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

    //Get all Customers
    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {

        return CustomerRepo.findAll();
    }


    //Create
    @PostMapping("/customer")
    public void createCustomer(@RequestBody Customer e) throws IOException {
        String RandomPwd = randomizer();
        String HashedPwd = passwordEncoder.encode(RandomPwd);
        e.setPassword(HashedPwd);
        this.emailsender(RandomPwd);
        CustomerRepo.save(e);
    }
    //Find by Id
    @GetMapping("/customer/{id}")
    public Optional<Customer> getCustomerviaId(@PathVariable(value = "id") long custid){

        return CustomerRepo.findById(custid);
    }
    //Find By Email
    @GetMapping("/customer/c/{email}")
    public List<Customer> getCustomerViaEmail(@PathVariable(value = "email") String mail){

        return CustomerRepo.findByEmail(mail);


    }
    //Find only Box related to this Customer
    @GetMapping("/customer/id/{id}")
    public Collection<Box> pwdCustomer(@PathVariable(value = "id" ) long custid) {

        Collection<Box> a = null ;
        Optional<Customer> c = CustomerRepo.findById(custid);
        if(c.isPresent()) {

            Customer o = c.get();

            a =  o.getPack();
        }
        return a;
    }

    public void emailsender(String em) throws IOException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        String template = "{\r\n    \"personalizations\": [\r\n        {\r\n            \"to\": [\r\n                {\r\n                    \"email\": \"zqdiac12022@gmail.com\"\r\n                }\r\n            ],\r\n            \"subject\": \"DeliveryApp login\"\r\n        }\r\n    ],\r\n    \"from\": {\r\n        \"email\": \"DeliveryTeam@Support.com\"\r\n    },\r\n    \"content\": [\r\n        {\r\n            \"type\": \"text/html\",\r\n            \"value\": \"password : <h1> %s </h1>\"\r\n        }\r\n    ]\r\n}";

        com.squareup.okhttp.RequestBody body = com.squareup.okhttp.RequestBody.create(mediaType, String.format(template,em));
        Request request = new Request.Builder()
                .url("https://rapidprod-sendgrid-v1.p.rapidapi.com/mail/send")
                .post(body)
                .addHeader("content-type", "application/json")
                .addHeader("X-RapidAPI-Host", "rapidprod-sendgrid-v1.p.rapidapi.com")
                .addHeader("X-RapidAPI-Key", "02113d22b9msh4634ed9d8a484eap174484jsn0f04a8410583")
                .build();

        Response response = client.newCall(request).execute();
    }

    public String randomizer(){
        String password = new Random().ints(10, 33, 122).collect(StringBuilder::new,
                        StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    return password;
    }



}
