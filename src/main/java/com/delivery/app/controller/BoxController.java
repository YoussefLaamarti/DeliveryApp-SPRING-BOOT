package com.delivery.app.controller;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.delivery.app.model.Box;

import com.delivery.app.model.Customer;
import com.delivery.app.model.Delivery_man;
import com.delivery.app.model.STATE;
import com.delivery.app.repository.BoxRepository;
import com.delivery.app.repository.CustomerRepository;
import com.delivery.app.repository.DeliveryManRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
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
public class BoxController {

	@Autowired
	private BoxRepository BoxRepo;
	@Autowired
	private CustomerRepository Custrepo;
	@Autowired
	private DeliveryManRepository delivrepo;

// Get all Boxes V
	@GetMapping("/boxes")
	public List<Box> getAllBoxes() {

		return BoxRepo.findAll();
	}

//  CREATE A BOX V [ SELLER ]
	@PostMapping("/box")
	@PostAuthorize("hasAuthority('SELLER')")
    public ResponseEntity<Box> createBox(@RequestBody Box e) {

		BoxRepo.save(e);
		return new ResponseEntity<>(e, HttpStatus.CREATED);
	}



	// DELETE A BOX BY ID V
	@DeleteMapping("/box/{id}")
    public void delBox(@PathVariable(value = "id") long colid){

		BoxRepo.deleteById(colid);
    }
	
	/*GET A BOX BY ID V [DELIVERYMAN]
		@GetMapping("/box/{id}")
		@PostAuthorize("hasAuthority('DELIVERY_MAN')")
	    public Optional<Box> getBoxViaId(@PathVariable(value = "id") long colid){

			return BoxRepo.findById(colid);
	    }
	    */

		//UPDATE STATUS FOR A BOX V [DELIVERY MAN]
		@PutMapping("/box/us/{id}")
		@PostAuthorize("hasAuthority('DELIVERY_MAN')")
		public void updateBox(@PathVariable(value = "id" ) long colisid, @RequestBody Box ColisDetails) {
			
		
		Optional<Box> c = BoxRepo.findById(colisid);
		
		if(c.isPresent()) {
			
			Box o = c.get();
			
			o.setStatus(ColisDetails.getStatus());

			if(o.getStatus().toString() == "DELIVERED"){

				o.setDate(new Date());
			}
			
			BoxRepo.save(o);
		
		} 
		}
		//UPDATE DELIVERY MAN Via id [DELIVERYMAN]
		@PutMapping("/box/ud/{id}/{id_d}")
		@PostAuthorize("hasAuthority('DELIVERY_MAN')")
		public String updatedeBox(@PathVariable(value = "id" ) long colisid,@PathVariable(value = "id_d" ) long deleveryid) {


			Optional<Box> b = BoxRepo.findById(colisid);
			Optional<Delivery_man> d = delivrepo.findById(deleveryid);
			if(d.isPresent() && b.isPresent()) {

				Box o = b.get();
				Delivery_man z = d.get();

				o.setDelivery_man(z);

                o.setStatus(STATE.IN_PROGRESS);

				BoxRepo.save(o);

				return "Howa hadak";

			}
			return "erreur";



		}
	//UPDATE BOX CUSTOMER ID V [SELLER]
	@PutMapping("/box/uc/{id}/{id_c}")
	@PostAuthorize("hasAuthority('SELLER')")
	public String updatecuBox(@PathVariable(value = "id" ) long colisid,@PathVariable(value = "id_c" ) long customerid) {


		Optional<Box> b = BoxRepo.findById(colisid);
		Optional<Customer> c = Custrepo.findById(customerid);
		if(c.isPresent() && b.isPresent()) {

			Box o = b.get();
			Customer z = c.get();

			o.setCustomer(z);

			BoxRepo.save(o);

			return "Howa hadak";

		}
		return "erreur";



	}
		
		

		//FIND A BOX BY Code [ANY]
		@GetMapping("/box/ref/{code}")
	    public Box getColistViaId(@PathVariable(value = "code") String colcode){

			return BoxRepo.findByCode(colcode);
	    }
    // Get all  pending Boxes V [DELIVERY_MAN] V
    @GetMapping("/pendingboxes")
	@PostAuthorize("hasAuthority('DELIVERY_MAN')")
    public List<Box> getAllpendingBoxes() {
        List<Box> a;
       a = BoxRepo.findAll();

       List<Box> b ;
       b = new ArrayList<>();

       for (Box bx : a){
           if(bx.getDelivery_man() == null){
            b.add(bx);
           }
       }

       return b ;
    }

}
