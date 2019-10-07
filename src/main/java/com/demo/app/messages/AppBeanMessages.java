package com.demo.app.messages;

import com.demo.app.exception.AppException;

/**
 * 
 * Enum for application message keys
 * 
 */
public enum AppBeanMessages implements IMessageKey {
	OPERATION_ERROR, 
	PERSISTENCE_ERROR
	;

	public String getName()
	{
		return name();
	}

	public AppException create()
	{
		return new AppException(this.toString());
	}


	public AppException create(Throwable e, String message)
	{
		return new AppException(message, e);
	}
}
