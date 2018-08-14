package com.hackerrank.sample.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackerrank.sample.model.Inventory;
import com.hackerrank.sample.service.RetailerService;

@RunWith(SpringRunner.class)
@WebMvcTest(InventoryController.class)
public class InventoryControllerTest {
	
	private Inventory inventory;

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	@Qualifier("inventoryService")
	RetailerService<Inventory> retailerService;
	
	@Before
	public void initialize() {
		inventory = getInventory();
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
	
	@Test
	public void testgetAll() throws Exception {
		List<Inventory> list = new ArrayList<>();
		list.add(inventory);
		Mockito.when(retailerService.getAll()).thenReturn(list);
		mvc.perform(MockMvcRequestBuilders.get("/item").contentType(MediaType.APPLICATION_JSON))
		//.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$").isNotEmpty());
	}
	
	@Test
	public void testgetByID() throws Exception {
		Mockito.when(retailerService.get(10)).thenReturn(inventory);
		mvc.perform(MockMvcRequestBuilders.get("/item/10").contentType(MediaType.APPLICATION_JSON))
		//.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$").isNotEmpty());
	}
	
	@Test
	public void testgetByIDNotFound() throws Exception {
		Mockito.when(retailerService.get(10)).thenReturn(null);
		mvc.perform(MockMvcRequestBuilders.get("/item/10").contentType(MediaType.APPLICATION_JSON))
		//.andDo(print())
		.andExpect(status().isNotFound());
	}
	
	@Test
	public void testDeleteByIDNotFound() throws Exception {
		Mockito.when(retailerService.delete(10)).thenReturn(false);
		mvc.perform(MockMvcRequestBuilders.delete("/item/10").contentType(MediaType.APPLICATION_JSON))
		//.andDo(print())
		.andExpect(status().isNotFound());
	}
	
	@Test
	public void testDeleteByID() throws Exception {
		Mockito.when(retailerService.delete(10)).thenReturn(true);
		mvc.perform(MockMvcRequestBuilders.delete("/item/10").contentType(MediaType.APPLICATION_JSON))
		//.andDo(print())
		.andExpect(status().isOk());
	}
	
	@Test
	public void testDeleteAll() throws Exception {
		mvc.perform(MockMvcRequestBuilders.delete("/item").contentType(MediaType.APPLICATION_JSON))
		//.andDo(print())
		.andExpect(status().isOk());
	}
	
	@Test
	public void testCreate() throws Exception {
		Mockito.when(retailerService.create((Inventory)Mockito.any())).thenReturn(inventory);
		mvc.perform(MockMvcRequestBuilders.post("/item")
			.contentType(MediaType.APPLICATION_JSON)
			.content(getJSON(inventory)))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$").isNotEmpty());
	}
	
	@Test
	public void testUpdate() throws Exception {
		Mockito.when(retailerService.update((Inventory)Mockito.any(), Mockito.anyInt())).thenReturn(inventory);
		mvc.perform(MockMvcRequestBuilders.put("/item/10")
			.contentType(MediaType.APPLICATION_JSON)
			.content(getJSON(inventory)))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$").isNotEmpty());
	}
	
	@Test
	public void testUpdateNoUpdate() throws Exception {
		Mockito.when(retailerService.update((Inventory)Mockito.any(), Mockito.anyInt())).thenReturn(null);
		mvc.perform(MockMvcRequestBuilders.put("/item/10")
			.contentType(MediaType.APPLICATION_JSON)
			.content(getJSON(inventory)))
			.andExpect(status().isNotFound());
	}
	
	private String getJSON (Inventory inventory) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(inventory);
		return jsonInString;
	}

}
