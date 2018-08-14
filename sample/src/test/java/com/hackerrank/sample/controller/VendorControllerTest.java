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
import com.hackerrank.sample.model.Vendor;
import com.hackerrank.sample.service.RetailerService;

@RunWith(SpringRunner.class)
@WebMvcTest(VendorController.class)
public class VendorControllerTest {

	private Vendor vendor;
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	@Qualifier("vendorService")
	RetailerService<Vendor> retailerService;

	@Before
	public void setUp() throws Exception {
		vendor = getVendor();
	}
	
	public Vendor getVendor() {
		Vendor vendor = new Vendor();
		vendor.setVendorId(10001);
		vendor.setVendorName("Vendor_001");
		vendor.setVendorUsername("vendor_1");
		vendor.setVendorEmail("vendor_1@gmail.com");
		vendor.setVendorAddress("Ranchi");
		vendor.setVendorContactNo(9876543);
		return vendor;
	}
	

	@Test
	public void testgetAll() throws Exception {
		List<Vendor> list = new ArrayList<>();
		list.add(vendor);
		Mockito.when(retailerService.getAll()).thenReturn(list);
		mvc.perform(MockMvcRequestBuilders.get("/vendor").contentType(MediaType.APPLICATION_JSON))
		//.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$").isNotEmpty());
	}
	
	@Test
	public void testgetByID() throws Exception {
		Mockito.when(retailerService.get(10)).thenReturn(vendor);
		mvc.perform(MockMvcRequestBuilders.get("/vendor/10").contentType(MediaType.APPLICATION_JSON))
		//.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$").isNotEmpty());
	}
	
	@Test
	public void testgetByIDNotFound() throws Exception {
		Mockito.when(retailerService.get(10)).thenReturn(null);
		mvc.perform(MockMvcRequestBuilders.get("/vendor/10").contentType(MediaType.APPLICATION_JSON))
		//.andDo(print())
		.andExpect(status().isNotFound());
	}
	
	@Test
	public void testDeleteByIDNotFound() throws Exception {
		Mockito.when(retailerService.delete(10)).thenReturn(false);
		mvc.perform(MockMvcRequestBuilders.delete("/vendor/10").contentType(MediaType.APPLICATION_JSON))
		//.andDo(print())
		.andExpect(status().isNotFound());
	}
	
	@Test
	public void testDeleteByID() throws Exception {
		Mockito.when(retailerService.delete(10)).thenReturn(true);
		mvc.perform(MockMvcRequestBuilders.delete("/vendor/10").contentType(MediaType.APPLICATION_JSON))
		//.andDo(print())
		.andExpect(status().isOk());
	}
	
	@Test
	public void testDeleteAll() throws Exception {
		mvc.perform(MockMvcRequestBuilders.delete("/vendor").contentType(MediaType.APPLICATION_JSON))
		//.andDo(print())
		.andExpect(status().isOk());
	}
	
	@Test
	public void testCreate() throws Exception {
		Mockito.when(retailerService.create((Vendor)Mockito.any())).thenReturn(vendor);
		mvc.perform(MockMvcRequestBuilders.post("/vendor")
			.contentType(MediaType.APPLICATION_JSON)
			.content(getJSON(vendor)))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$").isNotEmpty());
	}
	
	@Test
	public void testUpdate() throws Exception {
		Mockito.when(retailerService.update((Vendor)Mockito.any(), Mockito.anyInt())).thenReturn(vendor);
		mvc.perform(MockMvcRequestBuilders.put("/vendor/10")
			.contentType(MediaType.APPLICATION_JSON)
			.content(getJSON(vendor)))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$").isNotEmpty());
	}
	
	@Test
	public void testUpdateNoUpdate() throws Exception {
		Mockito.when(retailerService.update((Vendor)Mockito.any(), Mockito.anyInt())).thenReturn(null);
		mvc.perform(MockMvcRequestBuilders.put("/vendor/10")
			.contentType(MediaType.APPLICATION_JSON)
			.content(getJSON(vendor)))
			.andExpect(status().isNotFound());
	}
	
	private String getJSON (Vendor vendor) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(vendor);
		return jsonInString;
	}

}
