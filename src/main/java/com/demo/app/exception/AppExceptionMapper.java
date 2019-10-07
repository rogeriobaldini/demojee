package com.demo.app.exception;

import java.util.logging.Logger;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Application Exception Mapper
 * Provider to send any app exception message to rest response
 * 
 * @author rogerio.baldini
 *
 */
@Provider
public class AppExceptionMapper implements ExceptionMapper<AppException> {
	
	private final static Logger LOGGER = Logger.getLogger(AppExceptionMapper.class.getName());
	
	@Override
	public Response toResponse(AppException exception) {
		LOGGER.warning("AppExceptionMapper.toResponse");
		if (	exception.getCause() instanceof NoResultException || 
				exception.getCause() instanceof EntityNotFoundException) {
			LOGGER.warning("AppExceptionMapper.toResponse: SC_NOT_FOUND");
			return Response.status(404).entity(exception.getMessage()).type("text/plain").build();
		}
		LOGGER.severe("AppExceptionMapper.toResponse: serverError");
		return Response.serverError().entity(exception.getMessage()).type("text/plain").build();
	}

}
