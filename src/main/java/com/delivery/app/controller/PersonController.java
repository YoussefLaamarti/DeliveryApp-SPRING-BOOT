/*package com.delivery.app.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.delivery.app.model.Box;
import com.delivery.app.model.Person;
import com.delivery.app.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;





@RestController
@CrossOrigin("*")
public class PersonController {
	
	 @Autowired
	    private PersonRepository PersonRepo;
	 
	//Get all Persons 
	@GetMapping("/getPersons")
    public List<Person> getAllPersons() {
     
        return PersonRepo.findAll();
    }

	//Create Person
	@PostMapping("/person")
    public void createPerson(@RequestBody Person e) {

		PersonRepo.save(e);

    }
	
	
	//Delete  Person via Id
	@DeleteMapping("/person/{id}")
    public void delAPerson(@PathVariable(value = "id") long cliid){

		PersonRepo.deleteById(cliid);
    }
	
	//Get Person by Id
	@GetMapping("/person/{id}")
    public Optional<Person> getPersonViaId(@PathVariable(value = "id") long cliid){

		return PersonRepo.findById(cliid);
    }
	
	//UPDATE Client 
	@PutMapping("/person/{id}")
	public void updateClient(@PathVariable(value = "id" ) long clientid, @RequestBody Person ClientDetails) {
		
	
	Optional<Person> c = PersonRepo.findById(clientid);
	
	if(c.isPresent()) {

		Person o = c.get();
		o.setUsername(ClientDetails.getUsername());
		o.setPassword(ClientDetails.getPassword());

		PersonRepo.save(o);
	
	} 

	
	}

	//Find By Username
		@GetMapping("/person/u/{username}")
	    public List<Person> getPersonViaUsername(@PathVariable(value = "username") String usernn){

			return PersonRepo.findByName(usernn);
	    

		}
		/*
		//Find only Box related to this Person
		@GetMapping("/person/u_id/{id}")
		public Collection<Box> pwdPerson(@PathVariable(value = "id" ) long clientid) {
			
		Collection<Box> a = null ;
		Optional<Person> c = PersonRepo.findById(clientid);
		if(c.isPresent()) {

			Person o = c.get();
		
		a =  o.getPack();
		} 
		return a;
		}


		 */





