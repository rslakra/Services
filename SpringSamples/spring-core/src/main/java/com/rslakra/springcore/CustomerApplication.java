/**
 * 
 */
package com.rslakra.springcore;

import com.rslakra.springcore.service.CustomerService;
import com.rslakra.springcore.service.CustomerServiceImpl;

/**
 * @author Rohtash Singh Lakra
 * @version 1.0.0
 *
 */
public class CustomerApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CustomerService customerService = new CustomerServiceImpl();
		System.out.println(customerService.findCustomers());
	}

}
