/**
 * 
 */
package com.rslakra.springcore.service;

import java.util.List;

import com.rslakra.springcore.repository.CustomerRepository;
import com.rslakra.springcore.model.Customer;
import com.rslakra.springcore.repository.CustomerRepositoryImpl;

/**
 * @author Rohtash Singh Lakra
 * @version 1.0.0
 *
 */
public class CustomerServiceImpl implements CustomerService {

	private CustomerRepository customerRepository = new CustomerRepositoryImpl();

	/**
	 * (non-Javadoc)
	 * 
	 * @see CustomerService#findCustomers()
	 */
	@Override
	public List<Customer> findCustomers() {
		return customerRepository.findCustomers();
	}

}
