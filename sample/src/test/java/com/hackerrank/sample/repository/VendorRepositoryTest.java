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

import com.hackerrank.sample.model.Vendor;

@RunWith(SpringRunner.class)
@DataJpaTest
public class VendorRepositoryTest {

	@Autowired
	VendorRepository repo;
	
	Vendor vendor;

	@Before
	public void setUp() throws Exception {
		vendor = getVendor();
	}

	@Test
	public void findAvailabilityTest() throws ParseException {
		Optional<Vendor> vendor = repo.findById(10001);
		assertTrue(vendor.isPresent());
	}

	@Test
	public void saveTest() throws ParseException {
		Vendor savedVendor = repo.save(vendor);
		assertTrue(savedVendor.getVendorId() == 10001);
	}

	@Test
	public void findAllTest() {
		List<Vendor> list = (List<Vendor>) repo.findAll();
		assertNotNull(list);
		assertTrue(list.size() > 0);
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

}
