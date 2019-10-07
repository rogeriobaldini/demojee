package com.demo.app.rest;

import java.io.Serializable;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.demo.app.entity.IEntity;
import com.demo.app.exception.AppException;

/**
 * 
 * Generic rest interface to define CRUD operations
 * 
 * @author rogerio.baldini
 *
 * @param <PK> Primary Key
 * @param <E> Entity
 */
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface IEntityRest<PK extends Serializable, E extends IEntity<PK>> {

	@GET
	@Path("/")
	List<E> retrieve() throws AppException;

	@GET
	@Path("/{id}")
	E get(@PathParam("id") PK entityId) throws AppException;

	@POST
	@Path("/create")
	E create(E entity) throws AppException;

	@PUT
	@Path("/{id}")
	E update(E entity) throws AppException;

	@DELETE
	@Path("/{id}")
	boolean delete(E entity) throws AppException;


}
