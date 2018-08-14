package com.hackerrank.sample.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackerrank.sample.model.Vendor;
import com.hackerrank.sample.repository.VendorRepository;

@Service("vendorService")
public class VendorServiceImpl implements RetailerService<Vendor> {
	
	@Autowired
	VendorRepository vendorRepository;

	@Override
	public Vendor create(Vendor t) {
		return vendorRepository.save(t);
		
	}

	@Override
	public Vendor update(Vendor t, int id) {
		Vendor existingVendor = get(id);
		if (existingVendor != null) {
			existingVendor = t;
			vendorRepository.save(existingVendor);
		}
		return existingVendor;
	}

	@Override
	public List<Vendor> getAll() {
		List<Vendor> vendors = new ArrayList<>();
		vendorRepository.findAll().iterator().forEachRemaining(vendors::add);
		return vendors;
	}

	@Override
	public Vendor get(int id) {
		Vendor vendor = null;
		Optional<Vendor> optionalVendor = vendorRepository.findById(id);
		if (optionalVendor.isPresent()) {
			vendor = optionalVendor.get();
		}
		return vendor;
	}

	@Override
	public boolean delete(int id) {
		Vendor vendor = get(id);
		if (vendor != null) {
			vendorRepository.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public void deleteAll() {
		vendorRepository.deleteAll();
	}
}
