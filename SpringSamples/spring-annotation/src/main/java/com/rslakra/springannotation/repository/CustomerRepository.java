/**
 * 
 */
package com.rslakra.springannotation.repository;

import java.util.List;

import com.rslakra.springannotation.model.Customer;

/**
 * @author Rohtash Singh Lakra
 * @version 1.0.0
 *
 */
public interface CustomerRepository {

	/**
	 * Returns the list of customers.
	 * 
	 * @return
	 */
	List<Customer> findCustomers();

}
