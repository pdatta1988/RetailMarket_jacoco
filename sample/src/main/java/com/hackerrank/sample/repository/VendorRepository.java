package com.hackerrank.sample.repository;

import org.springframework.data.repository.CrudRepository;

import com.hackerrank.sample.model.Vendor;

	public interface VendorRepository extends CrudRepository<Vendor, Integer> {

}
