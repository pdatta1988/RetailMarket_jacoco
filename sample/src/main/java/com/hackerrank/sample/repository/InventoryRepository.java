package com.hackerrank.sample.repository;

import org.springframework.data.repository.CrudRepository;

import com.hackerrank.sample.model.Inventory;

public interface InventoryRepository extends CrudRepository<Inventory, Integer> {

}
