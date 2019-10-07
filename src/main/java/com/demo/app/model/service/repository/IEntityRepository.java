package com.demo.app.model.service.repository;

import java.io.Serializable;
import java.util.List;

import com.demo.app.entity.IEntity;
import com.demo.app.exception.AppException;

/**
 * Generic repository interface to define CRUD operations
 * 
 * @author rogerio.baldini
 *
 * @param <PK> Primary Key
 * @param <E> Entity
 */
public interface IEntityRepository<PK extends Serializable, E extends IEntity<PK>> {
	
   Class<E> getEntityType();

   List<E> retrieve() throws AppException;
   
   E get(PK id);

   E getDetached(PK id);

   E update(E entity) throws AppException;

   void delete(E entity);

}