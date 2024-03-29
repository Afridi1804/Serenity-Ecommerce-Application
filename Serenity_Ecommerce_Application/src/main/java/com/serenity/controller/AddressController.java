package com.serenity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.serenity.exception.AddressException;
import com.serenity.exception.CustomersException;
import com.serenity.model.Address;
import com.serenity.service.AddressService;

@RestController
@RequestMapping("/Address")
public class AddressController {

	@Autowired
	public AddressService addressService;

	@PostMapping("/addAdress")
	public ResponseEntity<Address> addAddress(@RequestBody Address address) throws AddressException {
		Address ad = addressService.addAddress(address);
		return new ResponseEntity<>(ad, HttpStatus.CREATED);
	}

	
	@GetMapping("/getAddressByCustomerId/{aid}")
	public ResponseEntity<Address> getAddressByCustomerId(@PathVariable int aid)
			throws AddressException, CustomersException {

		Address add = addressService.getAddressByCustomerId(aid);
		return new ResponseEntity<>(add, HttpStatus.OK);
	}

	@PutMapping("/updateAddressByCustomerId/{aid}")
	public ResponseEntity<Address> updateAddressByCustomerId(@PathVariable int aid, @RequestBody Address address)
			throws AddressException, CustomersException {
		Address add = addressService.updateAddressByCustomerId(aid, address);
		return new ResponseEntity<>(add, HttpStatus.OK);
	}

	@DeleteMapping("/deleteAddressByCustomerId/{aid}")
	public ResponseEntity<Address> deleteAddressByCustomerId(@PathVariable int aid)
			throws AddressException, CustomersException {
		Address add = addressService.deleteAddressByCustomerId(aid);
		return new ResponseEntity<>(add, HttpStatus.OK);

	}

}
