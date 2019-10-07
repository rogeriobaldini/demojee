package com.demo.app.model.service.repository;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.demo.app.entity.IEntity;
import com.demo.app.exception.AppException;
import com.demo.app.messages.AppBeanMessages;

/**
 * Generic repository class to implement CRUD operations
 * 
 * 
 * @author rogerio.baldini
 *
 * @param <PK> Primary Key
 * @param <E> Entity
 */
public abstract class AbstractBaseRepository<PK extends Serializable, E extends IEntity<PK>> implements IEntityRepository<PK, E>  {

	private final static Logger LOGGER = Logger.getLogger(AbstractBaseRepository.class.getName());	
	
	protected abstract EntityManager getEntityManager();

	public abstract Class<E> getEntityType();

	@Override
	public E get(PK id) {

		try {
			LOGGER.info("AbstractBaseRepository.get: "+id);
			return getEntityManager().find(getEntityType(), id);
		} catch (AppException e) {
			LOGGER.severe("AbstractBaseRepository.get AppException: "+e.getMessage());
			throw e;
		} catch (Exception e) {
			LOGGER.severe("AbstractBaseRepository.get Exception: "+e.getMessage());
			throw AppBeanMessages.PERSISTENCE_ERROR.create(e, e.getMessage());
		}
	}


	@Override
	public E getDetached(PK id) {

		try {
			LOGGER.info("AbstractBaseRepository.getDetached: "+id);
			E ent = getEntityManager().find(getEntityType(), id);
			getEntityManager().detach(ent);
			return ent;
		} catch (AppException e) {
			LOGGER.severe("AbstractBaseRepository.getDetached AppException: "+e.getMessage());
			throw e;
		} catch (Exception e) {
			LOGGER.severe("AbstractBaseRepository.getDetached Exception: "+e.getMessage());
			throw AppBeanMessages.PERSISTENCE_ERROR.create(e, e.getMessage());
		}
	}

	@Override
	public E update(E entity) {
		try {
			LOGGER.info("AbstractBaseRepository.update ");
			if (entity.getId() == null) {
				getEntityManager().persist(entity);
			} else {
				LOGGER.info("AbstractBaseRepository.update: "+entity.getId());
				entity = getEntityManager().merge(entity);
			}
			getEntityManager().flush();
		} catch (AppException e) {
			LOGGER.severe("AbstractBaseRepository.update AppException: "+e.getMessage());
			throw e;
		} catch (Exception e) {
			LOGGER.severe("AbstractBaseRepository.update Exception: "+e.getMessage());
			throw AppBeanMessages.PERSISTENCE_ERROR.create(e, e.getMessage());
		}

		return entity;
	}

	@Override
	public void delete(E entity) {
		try {
			LOGGER.info("AbstractBaseRepository.delete: "+entity);
			getEntityManager().remove(entity);
			getEntityManager().flush();
		} catch (AppException e) {
			LOGGER.severe("AbstractBaseRepository.delete AppException: "+e.getMessage());
			throw e;
		} catch (Exception e) {
			LOGGER.severe("AbstractBaseRepository.delete Exception: "+e.getMessage());
			throw AppBeanMessages.PERSISTENCE_ERROR.create(e, e.getMessage());
		}
	}


	@Override
	public List<E> retrieve() {
		try {
			LOGGER.info("AbstractBaseRepository.retrieve");
			CriteriaBuilder builder = criteriaBuilder();

			CriteriaQuery<E> query = builder.createQuery(getEntityType());

			Root<E> from = query.from(getEntityType());

			query.select(from);

			return createQuery(query).getResultList();
		} catch (AppException e) {
			LOGGER.severe("AbstractBaseRepository.retrieve AppException: "+e.getMessage());
			throw e;
		} catch (Exception e) {
			LOGGER.severe("AbstractBaseRepository.retrieve Exception: "+e.getMessage());
			throw AppBeanMessages.PERSISTENCE_ERROR.create(e, e.getMessage());
		}
	}

	
	protected CriteriaBuilder criteriaBuilder() {

		try {
			return getEntityManager().getCriteriaBuilder();
		} catch (Exception e) {
			LOGGER.severe("AbstractBaseRepository.criteriaBuilder Exception: "+e.getMessage());
			throw AppBeanMessages.PERSISTENCE_ERROR.create(e, e.getMessage());
		}

	}

	protected <T> TypedQuery<T> createQuery(CriteriaQuery<T> query) {

		try {
			return getEntityManager().createQuery(query);
		} catch (Exception e) {
			LOGGER.severe("AbstractBaseRepository.createQuery Exception: "+e.getMessage());
			throw AppBeanMessages.PERSISTENCE_ERROR.create(e, e.getMessage());
		}
	}


}