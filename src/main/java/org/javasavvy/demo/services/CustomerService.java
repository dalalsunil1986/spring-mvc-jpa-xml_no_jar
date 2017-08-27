package org.javasavvy.demo.services;

import java.util.Date;
import java.util.List;

import org.javasavvy.demo.entity.Customer;

public interface CustomerService {
	
	public Customer addCustomer(String customerName, String country);
	public Customer updateCustomer(long customerId,String customerName, String country);
	public Customer getCustomer(long customerId);
	public List<Customer> getAllCustomers();
}
