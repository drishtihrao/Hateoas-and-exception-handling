package com.demo.rest.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.rest.dao.DaoImpl;
import com.demo.rest.exception.InvalidCustomerException;
import com.demo.rest.pojo.Customer;

@Component
public class ServiceImpl implements Service {

	@Autowired
	private DaoImpl dao; //= new DaoImpl();

	@Override
	public void addCustomer(Customer customer) {
		dao.addCustomer(customer);

	}

	@Override
	public Collection<Customer> viewAllCustomers() {
		return dao.viewAllCustomers();
	}

	@Override
	public void updateCustomer(Customer customer) {
		dao.updateCustomer(customer);
		
	}

	@Override
	public void deleteCustomer(int customerId) {
		dao.deleteCustomer(customerId);
		
	}

	@Override
	public Customer getCustomerById(int customerId) throws InvalidCustomerException, Exception {
		return dao.getCustomerById(customerId);
	}

}
