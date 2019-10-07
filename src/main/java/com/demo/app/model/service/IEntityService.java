package com.demo.app.model.service;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

import com.demo.app.entity.IEntity;
import com.demo.app.exception.AppException;

/**
 * Generic service interface to define CRUD operations
 * 
 * @author rogerio.baldini
 *
 * @param <PK> Primary Key
 * @param <E> Entity
 */
public interface IEntityService<PK extends Serializable, E extends IEntity<PK>> {
	
   List<E> retrieve() throws AppException;

   E get(PK id);

   E create(@Valid E entity) throws AppException;

   E update(@Valid E entity) throws AppException;

   void delete(E entity) throws AppException;
   
}
