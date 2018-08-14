package com.hackerrank.sample.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hackerrank.sample.model.Customer;
import com.hackerrank.sample.service.RetailerService;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

	@Autowired
	@Qualifier("customerService")
	RetailerService<Customer> customerService;

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Customer> create(@RequestBody @Valid Customer customer) {
		return new ResponseEntity<Customer>(customerService.create(customer), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json", produces="application/json")
	public ResponseEntity<Customer> update(@PathVariable(value = "id") int id,
			@RequestBody @Valid Customer customer) {
		Customer updatedCustomer = customerService.update(customer, id);
		if (updatedCustomer == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
		}
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Customer>> getAll() {
		return new ResponseEntity<List<Customer>>(customerService.getAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Customer> get(@PathVariable(value = "id") int id) {
		Customer customer = customerService.get(id);
		if (customer == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(customer, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Object> delete(@PathVariable(value = "id") int id) {
		boolean isDeleted = customerService.delete(id);
		if (isDeleted) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(method = RequestMethod.DELETE, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public void deleteAll() {
		customerService.deleteAll();
	}
}
