/**
 * 
 */
package com.rslakra.dspringcore.service;

import java.util.List;

import com.rslakra.dspringcore.model.Customer;
import com.rslakra.dspringcore.repository.CustomerRepository;

/**
 * @author Rohtash Singh Lakra
 * @version 1.0.0
 *
 */
public class CustomerServiceImpl implements CustomerService {

	private CustomerRepository customerRepository;

	public CustomerServiceImpl() {

	}

	/**
	 * 
	 * @param customerRepository
	 */
	public CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	/**
	 * The customerRepository to be set.
	 *
	 * @param customerRepository
	 */
	public void setCustomerRepository(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.rslakra.dspringcore.service.CustomerService#findCustomers()
	 */
	@Override
	public List<Customer> findCustomers() {
		return customerRepository.findCustomers();
	}

}
