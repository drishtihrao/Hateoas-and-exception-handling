package com.demo.rest.dao;

import java.util.Collection;

import com.demo.rest.exception.InvalidCustomerException;
import com.demo.rest.pojo.Customer;

public interface Dao {
	void addCustomer(Customer customer);
	Collection<Customer> viewAllCustomers();
	void updateCustomer(Customer customer);
	void deleteCustomer(int customerId);
	Customer getCustomerById(int customerId) throws InvalidCustomerException, Exception;
}
