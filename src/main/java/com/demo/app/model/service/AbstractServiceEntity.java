package com.demo.app.model.service;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import com.demo.app.entity.IEntity;
import com.demo.app.exception.AppException;
import com.demo.app.messages.AppBeanMessages;
import com.demo.app.model.service.repository.IEntityRepository;

/**
 *  Generic service class to implement CRUD operations
 * 
 * @author rogerio.baldini
 *
 * @param <PK> Primary Key
 * @param <E> Entity
 */
public abstract class AbstractServiceEntity <PK extends Serializable, E extends IEntity<PK>> implements IEntityService<PK, E> {

	private final static Logger LOGGER = Logger.getLogger(AbstractServiceEntity.class.getName());
	
	protected abstract IEntityRepository<PK, E> getEntityRepository();

	@Override
	public List<E> retrieve() throws AppException {
		LOGGER.info("AbstractServiceEntity.retrieve");
		return getEntityRepository().retrieve();
	}
	
	@Override
	public E get(PK id) {
		LOGGER.info("AbstractServiceEntity.get");
		return getEntityRepository().get(id);
	}

	@Override
	public E create(@Valid E entity) throws AppException {
		LOGGER.info("AbstractServiceEntity.create");
		return getEntityRepository().update(entity);
	}
	
	@Override
	public E update(@Valid E entity) throws AppException {
		LOGGER.info("AbstractServiceEntity.update");		
		return getEntityRepository().update(entity);
	}

	@Override
	public void delete(E entity) throws AppException {
		try {
			LOGGER.info("AbstractServiceEntity.delete");	
			E toBeDeleted = getEntityRepository().get(entity.getId());
			if(toBeDeleted!=null) {
				getEntityRepository().delete(toBeDeleted); 
			} else{
				LOGGER.severe("AbstractServiceEntity.delete: No entity found");
				throw new EntityNotFoundException("No entity found");
			}
		} catch (AppException e) {
			LOGGER.severe("AbstractServiceEntity.delete AppException: "+e.getMessage());
			throw e;
		} catch (Exception e) {
			LOGGER.severe("AbstractServiceEntity.delete Exception: "+e.getMessage());
			throw AppBeanMessages.PERSISTENCE_ERROR.create(e, e.getMessage());
		}
	}

}
