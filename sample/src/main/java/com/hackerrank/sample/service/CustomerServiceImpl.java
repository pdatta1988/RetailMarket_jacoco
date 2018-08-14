package com.hackerrank.sample.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.hackerrank.sample.model.Customer;
import com.hackerrank.sample.repository.CustomerRepository;

@Service("customerService")
@Primary
public class CustomerServiceImpl implements RetailerService<Customer> {

	@Autowired
	CustomerRepository customerRepository;

	@Override
	public Customer create(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public Customer update(Customer customer, int id) {
		Customer existingCustomer = get(id);
		if (existingCustomer != null) {
			existingCustomer = customer;
			customerRepository.save(existingCustomer);
		}
		return existingCustomer;
	}

	@Override
	public List<Customer> getAll() {
		List<Customer> customers = new ArrayList<>();
		customerRepository.findAll().iterator().forEachRemaining(customers::add);
		return customers;
	}

	@Override
	public Customer get(int id) {
		Optional<Customer> optionalCustomer = customerRepository.findById(id);
		Customer customer = null;
		if (optionalCustomer.isPresent()) {
			customer = optionalCustomer.get();
		}
		return customer;
	}

	@Override
	public boolean delete(int id) {
		Optional<Customer> customerOptional = Optional.ofNullable(get(id));
		if (customerOptional.isPresent()) {
			customerRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void deleteAll() {
		customerRepository.deleteAll();
	}
}
