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
import com.hackerrank.sample.model.Customer;
import com.hackerrank.sample.service.RetailerService;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

	private Customer customer;
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	@Qualifier("customerService")
	RetailerService<Customer> retailerService;
	
	@Before
	public void initialize() {
		customer = getCustomer();
	}
	
	public Customer getCustomer() {
		Customer customer = new Customer();
		customer.setAddress("Kolkata");
		customer.setContactNumber(890887l);
		customer.setCustomerId(1009);
		customer.setCustomerName("Rahane");
		customer.setGender("Male");
		return customer;
	}

	@Test
	public void testgetAll() throws Exception {
		List<Customer> list = new ArrayList<>();
		list.add(customer);
		Mockito.when(retailerService.getAll()).thenReturn(list);
		mvc.perform(MockMvcRequestBuilders.get("/customer").contentType(MediaType.APPLICATION_JSON))
		//.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$").isNotEmpty());
	}
	
	@Test
	public void testgetByID() throws Exception {
		Mockito.when(retailerService.get(10)).thenReturn(customer);
		mvc.perform(MockMvcRequestBuilders.get("/customer/10").contentType(MediaType.APPLICATION_JSON))
		//.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$").isNotEmpty());
	}
	
	@Test
	public void testgetByIDNotFound() throws Exception {
		Mockito.when(retailerService.get(10)).thenReturn(null);
		mvc.perform(MockMvcRequestBuilders.get("/customer/10").contentType(MediaType.APPLICATION_JSON))
		//.andDo(print())
		.andExpect(status().isNotFound());
	}
	
	@Test
	public void testDeleteByIDNotFound() throws Exception {
		Mockito.when(retailerService.delete(10)).thenReturn(false);
		mvc.perform(MockMvcRequestBuilders.delete("/customer/10").contentType(MediaType.APPLICATION_JSON))
		//.andDo(print())
		.andExpect(status().isNotFound());
	}
	
	@Test
	public void testDeleteByID() throws Exception {
		Mockito.when(retailerService.delete(10)).thenReturn(true);
		mvc.perform(MockMvcRequestBuilders.delete("/customer/10").contentType(MediaType.APPLICATION_JSON))
		//.andDo(print())
		.andExpect(status().isOk());
	}
	
	@Test
	public void testDeleteAll() throws Exception {
		mvc.perform(MockMvcRequestBuilders.delete("/customer").contentType(MediaType.APPLICATION_JSON))
		//.andDo(print())
		.andExpect(status().isOk());
	}
	
	@Test
	public void testCreate() throws Exception {
		Mockito.when(retailerService.create((Customer)Mockito.any())).thenReturn(customer);
		mvc.perform(MockMvcRequestBuilders.post("/customer")
			.contentType(MediaType.APPLICATION_JSON)
			.content(getJSON(customer)))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$").isNotEmpty());
	}
	
	@Test
	public void testUpdate() throws Exception {
		Mockito.when(retailerService.update((Customer)Mockito.any(), Mockito.anyInt())).thenReturn(customer);
		mvc.perform(MockMvcRequestBuilders.put("/customer/10")
			.contentType(MediaType.APPLICATION_JSON)
			.content(getJSON(customer)))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$").isNotEmpty());
	}
	
	@Test
	public void testUpdateNoUpdate() throws Exception {
		Mockito.when(retailerService.update((Customer)Mockito.any(), Mockito.anyInt())).thenReturn(null);
		mvc.perform(MockMvcRequestBuilders.put("/customer/10")
			.contentType(MediaType.APPLICATION_JSON)
			.content(getJSON(customer)))
			.andExpect(status().isNotFound());
	}
	
	private String getJSON (Customer customer) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(customer);
		return jsonInString;
	}
}
