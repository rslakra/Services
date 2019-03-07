/**
 * 
 */
package com.rslakra.dspringcore.service;

import java.util.List;

import com.rslakra.dspringcore.model.Customer;
import com.rslakra.dspringcore.repository.CustomerRepository;
import com.rslakra.dspringcore.repository.CustomerRepositoryImpl;

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
	 * @see com.rslakra.dspringcore.service.CustomerService#findCustomers()
	 */
	@Override
	public List<Customer> findCustomers() {
		return customerRepository.findCustomers();
	}

}
