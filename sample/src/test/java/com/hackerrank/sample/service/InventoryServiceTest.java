package com.hackerrank.sample.service;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.junit4.SpringRunner;

import com.hackerrank.sample.model.Inventory;
import com.hackerrank.sample.repository.InventoryRepository;

@RunWith(SpringRunner.class)
public class InventoryServiceTest {

	@InjectMocks
	@Qualifier("inventoryService")
	InventoryServiceImpl inventoryServiceImpl;
	
	@Mock
	InventoryRepository inventoryRepository;
	
	private Inventory inventory;

	@Before
	public void setUp() throws Exception {
		inventory = getInventory();
	}

	@Test
	public void testupdate() {
		Optional<Inventory> optionalInventory = Optional.of(inventory);
		Mockito.when(inventoryRepository.findById(10)).thenReturn(optionalInventory);
		Inventory expectedInventory = inventoryServiceImpl.update(inventory, 10);
		assertTrue(inventory == expectedInventory);
	}
	
	@Test
	public void testNoupdate() {
		Optional<Inventory> optionalInventory = Optional.ofNullable(null);
		Mockito.when(inventoryRepository.findById(10)).thenReturn(optionalInventory);
		Inventory expectedInventory = inventoryServiceImpl.update(inventory, 10);
		assertTrue(expectedInventory == null);
	}
	
	@Test
	public void testcreate() {
		Mockito.when(inventoryRepository.save(inventory)).thenReturn(inventory);
		Inventory expectedInventory = inventoryServiceImpl.create(inventory);
		assertTrue(expectedInventory.getSkuId() == 1009);
	}
	
	@Test
	public void testgetAll() {
		List<Inventory> list = new ArrayList<>();
		list.add(inventory);
		Iterable<Inventory> itr = list;
		Mockito.when(inventoryRepository.findAll()).thenReturn(itr);
		List<Inventory> expectedList = inventoryServiceImpl.getAll();
		assertTrue(expectedList.size() == 1);
	}
	
	@Test
	public void testget() {
		Optional<Inventory> optionalInventory = Optional.of(inventory);
		Mockito.when(inventoryRepository.findById(10)).thenReturn(optionalInventory);
		Inventory expectedInventory = inventoryServiceImpl.get(10);
		assertTrue(expectedInventory == inventory);
	}
	
	@Test
	public void testgetNull() {
		Optional<Inventory> optionalInventory = Optional.ofNullable(null);
		Mockito.when(inventoryRepository.findById(10)).thenReturn(optionalInventory);
		Inventory expectedInventory = inventoryServiceImpl.get(10);
		assertTrue(expectedInventory == null);
	}
	
	@Test
	public void testdeleteNull() {
		Optional<Inventory> optionalInventory = Optional.ofNullable(null);
		Mockito.when(inventoryRepository.findById(10)).thenReturn(optionalInventory);
		assertTrue(!inventoryServiceImpl.delete(10));
	}
	
	@Test
	public void testdeleteNotNull() {
		Optional<Inventory> optionalInventory = Optional.ofNullable(inventory);
		Mockito.when(inventoryRepository.findById(10)).thenReturn(optionalInventory);
		assertTrue(inventoryServiceImpl.delete(10));
	}
	
	@Test
	public void testdeleteAll() {
		inventoryServiceImpl.deleteAll();
		assertTrue(true);
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
