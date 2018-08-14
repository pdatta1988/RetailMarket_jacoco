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
import com.hackerrank.sample.model.Inventory;
import com.hackerrank.sample.service.RetailerService;

@RestController
@RequestMapping(value="/item")
public class InventoryController {
	
	@Autowired
	@Qualifier("inventoryService")
	RetailerService<Inventory> inventoryService;

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Inventory> create(@RequestBody @Valid Inventory t) {
		return new ResponseEntity<Inventory>(inventoryService.create(t), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<Inventory> update(@PathVariable(value = "id") int id,
			@RequestBody @Valid Inventory t) {
		Inventory inventory = inventoryService.update(t, id);
		if (inventory == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(inventory, HttpStatus.OK);
		}
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Inventory>> getAll() {
		return new ResponseEntity<List<Inventory>>(inventoryService.getAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Inventory> get(@PathVariable(value = "id") int id) {
		Inventory inventory = inventoryService.get(id);
		if (inventory == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(inventory, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Object> delete(@PathVariable(value = "id") int id) {
		boolean isDeleted = inventoryService.delete(id);
		if (isDeleted) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(method = RequestMethod.DELETE, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public void deleteAll() {
		inventoryService.deleteAll();
	}
}
