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

import com.hackerrank.sample.model.Vendor;
import com.hackerrank.sample.service.RetailerService;

@RestController
@RequestMapping(value = "/vendor")
public class VendorController {
	
	@Autowired
	@Qualifier("vendorService")
	RetailerService<Vendor> vendorService;

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Vendor> create(@RequestBody @Valid Vendor t) {
		return new ResponseEntity<Vendor>(vendorService.create(t), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<Vendor> update(@PathVariable(value = "id") int id,
			@RequestBody @Valid Vendor t) {
		Vendor updatedVendor = vendorService.update(t, id);
		if (updatedVendor == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(updatedVendor, HttpStatus.OK);
		}
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Vendor>> getAll() {
		return new ResponseEntity<List<Vendor>>(vendorService.getAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Vendor> get(@PathVariable(value = "id") int id) {
		Vendor vendor = vendorService.get(id);
		if (vendor == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(vendor, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Object> delete(@PathVariable(value = "id") int id) {
		boolean isDeleted = vendorService.delete(id);
		if (isDeleted) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(method = RequestMethod.DELETE, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public void deleteAll() {
		vendorService.deleteAll();
	}
}
