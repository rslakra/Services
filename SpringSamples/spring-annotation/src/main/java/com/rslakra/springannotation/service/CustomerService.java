/**
 * 
 */
package com.rslakra.springannotation.service;

import java.util.List;

import com.rslakra.springannotation.model.Customer;

/**
 * @author Rohtash Singh Lakra
 * @version 1.0.0
 *
 */
public interface CustomerService {

	/**
	 * Returns the list of <code>Customer</code>.
	 * 
	 * @return
	 */
	List<Customer> findCustomers();

}
