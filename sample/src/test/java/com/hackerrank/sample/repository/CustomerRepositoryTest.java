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

import com.hackerrank.sample.model.Customer;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryTest {

	@Autowired
	CustomerRepository repo;
	
	Customer customer;

	@Before
	public void setUp() throws Exception {
		customer = getCustomer();
	}

	@Test
	public void findAvailabilityTest() throws ParseException {
		Optional<Customer> customer = repo.findById(1001);
		assertTrue(customer.isPresent());
	}

	@Test
	public void saveTest() throws ParseException {
		Customer savedCustomer = repo.save(customer);
		assertTrue(savedCustomer.getCustomerId() == 10045);
	}

	@Test
	public void findAllTest() {
		List<Customer> list = (List<Customer>) repo.findAll();
		assertNotNull(list);
		assertTrue(list.size() > 0);
	}

	public Customer getCustomer() {
		Customer customer = new Customer();
		customer.setAddress("Kolkata");
		customer.setContactNumber(890887l);
		customer.setCustomerId(10045);
		customer.setCustomerName("Rahane");
		customer.setGender("Male");
		return customer;
	}
}
