package com.demo.app.model.service.repository;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.demo.app.entity.IEntity;

/**
 *  Generic repository class to define entity manager configuration
 * 
 * @author rogerio.baldini
 *
 * @param <PK> Primary Key
 * @param <E> Entity
 */
public abstract class AbstractRepository<PK extends Serializable, E extends IEntity<PK>> extends AbstractBaseRepository<PK, E> {

	@PersistenceContext(unitName = "product-persistence-unit")
	private EntityManager entityManager;

	@Override
	protected EntityManager getEntityManager()
	{
		return entityManager;
	}
}
