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

import com.hackerrank.sample.model.Vendor;
import com.hackerrank.sample.repository.VendorRepository;

@RunWith(SpringRunner.class)
public class VendorServiceTest {
	
	@InjectMocks
	@Qualifier("vendorService")
	VendorServiceImpl vendorServiceImpl;
	
	@Mock
	VendorRepository vendorRepository;

	private Vendor vendor;

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
	public void testupdate() {
		Optional<Vendor> optionalVendor = Optional.of(vendor);
		Mockito.when(vendorRepository.findById(10)).thenReturn(optionalVendor);
		Vendor expectedVendor = vendorServiceImpl.update(vendor, 10);
		assertTrue(vendor == expectedVendor);
	}
	
	@Test
	public void testNoupdate() {
		Optional<Vendor> optionalVendor = Optional.ofNullable(null);
		Mockito.when(vendorRepository.findById(10)).thenReturn(optionalVendor);
		Vendor expectedVendor = vendorServiceImpl.update(vendor, 10);
		assertTrue(expectedVendor == null);
	}
	
	@Test
	public void testcreate() {
		Mockito.when(vendorRepository.save(vendor)).thenReturn(vendor);
		Vendor expectedVendor = vendorServiceImpl.create(vendor);
		assertTrue(expectedVendor.getVendorId() == 10001);
	}
	
	@Test
	public void testgetAll() {
		List<Vendor> list = new ArrayList<>();
		list.add(vendor);
		Iterable<Vendor> itr = list;
		Mockito.when(vendorRepository.findAll()).thenReturn(itr);
		List<Vendor> expectedList = vendorServiceImpl.getAll();
		assertTrue(expectedList.size() == 1);
	}
	
	@Test
	public void testget() {
		Optional<Vendor> optionalVendor = Optional.of(vendor);
		Mockito.when(vendorRepository.findById(10)).thenReturn(optionalVendor);
		Vendor expectedVendor = vendorServiceImpl.get(10);
		assertTrue(expectedVendor == vendor);
	}
	
	@Test
	public void testgetNull() {
		Optional<Vendor> optionalVendor = Optional.ofNullable(null);
		Mockito.when(vendorRepository.findById(10)).thenReturn(optionalVendor);
		Vendor expectedVendor = vendorServiceImpl.get(10);
		assertTrue(expectedVendor == null);
	}
	
	@Test
	public void testdeleteNull() {
		Optional<Vendor> optionalVendor = Optional.ofNullable(null);
		Mockito.when(vendorRepository.findById(10)).thenReturn(optionalVendor);
		assertTrue(!vendorServiceImpl.delete(10));
	}
	
	@Test
	public void testdeleteNotNull() {
		Optional<Vendor> optionalVendor = Optional.ofNullable(vendor);
		Mockito.when(vendorRepository.findById(10)).thenReturn(optionalVendor);
		assertTrue(vendorServiceImpl.delete(10));
	}
	
	@Test
	public void testdeleteAll() {
		vendorServiceImpl.deleteAll();
		assertTrue(true);
	}

}
