package com.demo.app.exception;

import javax.ejb.ApplicationException;

/**
 * Application Exception
 * 
 * @author rogerio.baldini
 *
 */
@ApplicationException(rollback = true)
public class AppException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4088887544254385066L;

	public AppException() {
		super();
		
	}

	public AppException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public AppException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public AppException(String message) {
		super(message);
		
	}

	public AppException(Throwable cause) {
		super(cause);
		
	}


	
	
}
