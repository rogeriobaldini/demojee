package com.demo.app.rest;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;

import com.demo.app.entity.IEntity;
import com.demo.app.exception.AppException;
import com.demo.app.model.service.IEntityService;

/**
 * Generic rest class to implement CRUD operations
 * 
 * @author rogerio.baldini
 *
 * @param <PK> Primary Key
 * @param <E> Entity
 */
public abstract class AbstractRest <PK extends Serializable, E extends IEntity<PK>> implements IEntityRest<PK, E> {

	private final static Logger LOGGER = Logger.getLogger(AbstractRest.class.getName());
	
	protected abstract IEntityService<PK, E> getEntityService();

	@Context  
	private HttpServletResponse response;
	
	@Override
	public List<E> retrieve() throws AppException {
		LOGGER.info("AbstractRest.retrieve");
		List<E> r = getEntityService().retrieve();
		handleNoContent(r);
		LOGGER.info("AbstractRest.retrieve: "+r);
		return r;
	}

	@Override
	public E get(PK entityId) throws AppException {
		LOGGER.info("AbstractRest.get");
		E e=  getEntityService().get(entityId);
		LOGGER.info("AbstractRest.get: "+entityId+" "+e);
		return e;
	}

	@Override
	public E create(E entity) throws AppException {
		LOGGER.info("AbstractRest.create");
		E e = getEntityService().update(entity);
		response.setStatus(HttpServletResponse.SC_CREATED);
	    try {
	        response.flushBuffer();
	    }catch(Exception ex){
	    	LOGGER.severe("AbstractRest.create: Exception "+ex.getMessage());	  
	    }		
		LOGGER.info("AbstractRest.create: "+e);	    
		return e; 
	}

	@Override
	public E update(E entity) throws AppException {
		LOGGER.info("AbstractRest.update");	
		E e = getEntityService().update(entity);
		LOGGER.info("AbstractRest.update: "+e);	
		return e; 
	}
	

	@Override
	public boolean delete(E entity) throws AppException {
		LOGGER.info("AbstractRest.delete");
		getEntityService().delete(entity);
		LOGGER.info("AbstractRest.delete: true");
		return true;
	}

	protected void handleNoContent(List<E> r) {
		if (r==null || r.size()<=0) {
			new WebApplicationException(HttpServletResponse.SC_NO_CONTENT);	
		}
	}	


}