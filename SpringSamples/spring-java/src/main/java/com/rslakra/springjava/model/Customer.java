/**
 * 
 */
package com.rslakra.springjava.model;

/**
 * 
 * @author Rohtash Singh Lakra
 * @version 1.0.0
 */
//@Component
public class Customer {

	private String firstName;
	private String middleName;
	private String lastName;

	public Customer() {

	}

	/**
	 * Returns the value of the firstName.
	 *
	 * @return firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * The firstName to be set.
	 *
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Returns the value of the middleName.
	 *
	 * @return middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * The middleName to be set.
	 *
	 * @param middleName
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * Returns the value of the lastName.
	 *
	 * @return lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * The lastName to be set.
	 *
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Returns the string representation of this object.
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sBuilder = new StringBuilder();
		// first name
		if (getFirstName() != null) {
			sBuilder.append(getFirstName());
		}
		// middle name
		if (getMiddleName() != null) {
			sBuilder.append(" ").append(getMiddleName());
		}

		// last name
		if (getLastName() != null) {
			sBuilder.append(" ").append(getLastName());
		}

		return sBuilder.toString();
	}

}
