package com.hackerrank.sample.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hackerrank.sample.model.Inventory;

@RunWith(SpringRunner.class)
@DataJpaTest
public class InventoryRepositoryTest {

	@Autowired
	InventoryRepository repo;
	
	Inventory inventory;

	@Before
	public void setUp() throws Exception {
		inventory = getInventory();
	}

	@Test
	public void findAvailabilityTest() throws ParseException {
		Optional<Inventory> inventory = repo.findById(10001);
		assertTrue(inventory.isPresent());
	}

	@Test
	public void saveTest() throws ParseException {
		Inventory savedInventory = repo.save(inventory);
		assertTrue(savedInventory.getSkuId() == 1009);
	}

	@Test
	public void findAllTest() {
		List<Inventory> list = (List<Inventory>) repo.findAll();
		assertNotNull(list);
		assertTrue(list.size() > 0);
	}

	public Inventory getInventory() {
		Inventory inventory = new Inventory();
		inventory.setInventoryOnHand(100);
		inventory.setMinQtyReq(10);
		inventory.setPrice(100.00);
		inventory.setProductLabel("v2");
		inventory.setProductName("Samsung");
		inventory.setSkuId(1009);
		return inventory;
	}

}
