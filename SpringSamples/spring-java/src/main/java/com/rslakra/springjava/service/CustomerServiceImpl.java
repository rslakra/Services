/**
 * 
 */
package com.rslakra.springjava.service;

import java.util.List;

import com.rslakra.springjava.model.Customer;
import com.rslakra.springjava.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Rohtash Singh Lakra
 * @version 1.0.0
 *
 */
@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

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
