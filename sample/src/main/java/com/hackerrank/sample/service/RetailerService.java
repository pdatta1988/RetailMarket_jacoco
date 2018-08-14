package com.hackerrank.sample.service;

import java.util.List;

import com.hackerrank.sample.model.Customer;

public interface RetailerService<T> {
	
	public T create(T t);
	public T update(T t, int id);
	public List<T> getAll();
	public T get(int id);
	public boolean delete(int id);
	public void deleteAll();

}
