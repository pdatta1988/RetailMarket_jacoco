package com.hackerrank.sample.repository;

import org.springframework.data.repository.CrudRepository;

import com.hackerrank.sample.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

}
