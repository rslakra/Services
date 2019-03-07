/**
 * 
 */
package com.rslakra.dspringcore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rslakra.dspringcore.model.Customer;
import com.rslakra.dspringcore.repository.CustomerRepository;

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
	 * @see com.rslakra.dspringcore.service.CustomerService#findCustomers()
	 */
	@Override
	public List<Customer> findCustomers() {
		return customerRepository.findCustomers();
	}

}
