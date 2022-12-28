package com.rslakra.libraryservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Rohtash Lakra (rlakra)
 * @created 10/16/21 4:19 PM
 */
@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class AuthenticationException extends ServerException {

	/**
	 * 
	 * @param message
	 */
	public AuthenticationException(String message) {
		super(message);
	}

	/**
	 * 
	 * @param message
	 * @param throwable
	 */
	public AuthenticationException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
