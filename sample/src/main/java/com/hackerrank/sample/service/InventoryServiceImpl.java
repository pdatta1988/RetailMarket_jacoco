package com.hackerrank.sample.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackerrank.sample.model.Inventory;
import com.hackerrank.sample.repository.InventoryRepository;

@Service("inventoryService")
public class InventoryServiceImpl implements RetailerService<Inventory> {
	
	@Autowired
	InventoryRepository inventoryRepository;
	

	@Override
	public Inventory create(Inventory t) {
		return inventoryRepository.save(t);
		
	}

	@Override
	public Inventory update(Inventory t, int id) {
		Inventory oldInventry = get(id);
		if (oldInventry != null) {
			oldInventry = t;
			inventoryRepository.save(oldInventry);
		}
		return oldInventry;
	}

	@Override
	public List<Inventory> getAll() {
		List<Inventory> list = new ArrayList<>();
		inventoryRepository.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public Inventory get(int id) {
		Inventory inventory = null;
		Optional<Inventory> inventoryOptional = inventoryRepository.findById(id);
		if (inventoryOptional.isPresent()) {
			inventory = inventoryOptional.get();
		}
		return inventory;
	}

	@Override
	public boolean delete(int id) {
		Inventory inventory = get(id);
		if (inventory != null) {
			inventoryRepository.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public void deleteAll() {
		inventoryRepository.deleteAll();
	}


}
