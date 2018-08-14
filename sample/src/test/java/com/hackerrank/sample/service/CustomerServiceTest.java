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

import com.hackerrank.sample.model.Customer;
import com.hackerrank.sample.repository.CustomerRepository;

@RunWith(SpringRunner.class)
public class CustomerServiceTest {
	
	@InjectMocks
	@Qualifier("customerService")
	CustomerServiceImpl customerServiceImpl;
	
	@Mock
	CustomerRepository customerRepository;
	
	private Customer customer;

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
	public void testupdate() {
		Optional<Customer> optionalCustomer = Optional.of(customer);
		Mockito.when(customerRepository.findById(10)).thenReturn(optionalCustomer);
		Customer expectedCustomer = customerServiceImpl.update(customer, 10);
		assertTrue(customer == expectedCustomer);
	}
	
	@Test
	public void testNoupdate() {
		Optional<Customer> optionalCustomer = Optional.ofNullable(null);
		Mockito.when(customerRepository.findById(10)).thenReturn(optionalCustomer);
		Customer expectedCustomer = customerServiceImpl.update(customer, 10);
		assertTrue(expectedCustomer == null);
	}
	
	@Test
	public void testcreate() {
		Mockito.when(customerRepository.save(customer)).thenReturn(customer);
		Customer expectedCustomer = customerServiceImpl.create(customer);
		assertTrue(expectedCustomer.getCustomerId() == 1009);
	}
	
	@Test
	public void testgetAll() {
		List<Customer> list = new ArrayList<>();
		list.add(customer);
		Iterable<Customer> itr = list;
		Mockito.when(customerRepository.findAll()).thenReturn(itr);
		List<Customer> expectedList = customerServiceImpl.getAll();
		assertTrue(expectedList.size() == 1);
	}
	
	@Test
	public void testget() {
		Optional<Customer> optionalCustomer = Optional.of(customer);
		Mockito.when(customerRepository.findById(10)).thenReturn(optionalCustomer);
		Customer expectedCustomer = customerServiceImpl.get(10);
		assertTrue(expectedCustomer == customer);
	}
	
	@Test
	public void testgetNull() {
		Optional<Customer> optionalCustomer = Optional.ofNullable(null);
		Mockito.when(customerRepository.findById(10)).thenReturn(optionalCustomer);
		Customer expectedCustomer = customerServiceImpl.get(10);
		assertTrue(expectedCustomer == null);
	}
	
	@Test
	public void testdeleteNull() {
		Optional<Customer> optionalCustomer = Optional.ofNullable(null);
		Mockito.when(customerRepository.findById(10)).thenReturn(optionalCustomer);
		assertTrue(!customerServiceImpl.delete(10));
	}
	
	@Test
	public void testdeleteNotNull() {
		Optional<Customer> optionalCustomer = Optional.ofNullable(customer);
		Mockito.when(customerRepository.findById(10)).thenReturn(optionalCustomer);
		assertTrue(customerServiceImpl.delete(10));
	}
	
	@Test
	public void testdeleteAll() {
		customerServiceImpl.deleteAll();
		assertTrue(true);
	}
}
