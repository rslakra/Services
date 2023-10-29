/**
 * 
 */
package com.rslakra.springannotation.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.rslakra.springannotation.model.Customer;

/**
 * @author Rohtash Singh Lakra
 * @version 1.0.0
 *
 */
@Repository("customerRepository")
public class CustomerRepositoryImpl implements CustomerRepository {

	public CustomerRepositoryImpl() {

	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see CustomerRepository#findCustomers()
	 */
	@Override
	public List<Customer> findCustomers() {
		List<Customer> customers = new ArrayList<>();
		customers.add(newCustomer("Rohtash", "Lakra"));
		customers.add(newCustomer("Rohtash", "Singh"));
		customers.add(newCustomer("Rohtash", "Singh", "Lakra"));
		customers.add(newCustomer("Sangita", "Lakra"));

		return customers;

	}

	/**
	 * 
	 * @param firstName
	 * @param lastName
	 * @return
	 */
	private Customer newCustomer(String firstName, String lastName) {
		return newCustomer(firstName, null, lastName);
	}

	private Customer newCustomer(String firstName, String middleName, String lastName) {
		Customer customer = new Customer();
		customer.setFirstName(firstName);
		customer.setMiddleName(middleName);
		customer.setLastName(lastName);

		return customer;
	}

}
