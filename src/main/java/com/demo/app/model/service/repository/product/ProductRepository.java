package com.demo.app.model.service.repository.product;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import com.demo.app.entity.ImageEntity;
import com.demo.app.entity.ProductEntity;
import com.demo.app.exception.AppException;
import com.demo.app.messages.AppBeanMessages;
import com.demo.app.model.service.repository.AbstractRepository;

/**
 * Product Repository
 * 
 * @author rogerio.baldini
 *
 */
public class ProductRepository extends AbstractRepository<Long, ProductEntity> {

	private final static Logger LOGGER = Logger.getLogger(ProductRepository.class.getName());

	@Override
	public Class<ProductEntity> getEntityType() {
		return ProductEntity.class;
	}

	public List<ProductEntity> retrieveAll(String relationships) {
		try {
			LOGGER.info("ProductRepository.retrieveAll: "+relationships);
			String namedQuery = "ProductEntity.retrieveAll";
			if (relationships!=null && relationships.equals("i")) {
				namedQuery = "ProductEntity.retrieveAllI";
			} else if (relationships!=null && relationships.equals("p")) {
				namedQuery = "ProductEntity.retrieveAllP";
			} else if (relationships!=null && relationships.equals("ip")) {
				namedQuery = "ProductEntity.retrieveAllIP";
			}	

			Query query = getEntityManager().createNamedQuery(namedQuery);

			List<ProductEntity> list = (List<ProductEntity>)query.getResultList( );
			LOGGER.info("ProductRepository.retrieveAll: return "+list);
			return list;

		} catch (AppException e) {
			LOGGER.severe("ProductRepository.retrieveAll AppException: "+e.getMessage());
			throw e;
		} catch (Exception e) {
			LOGGER.severe("ProductRepository.retrieveAll Exception: "+e.getMessage());
			throw AppBeanMessages.PERSISTENCE_ERROR.create(e, e.getMessage());
		}
	}

	public ProductEntity get(Long id, String relationships) {
		try {
			LOGGER.info("ProductRepository.get: id "+id+" relationships:"+relationships);
			CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
			CriteriaQuery q = cb.createQuery(ProductEntity.class);
			Root o = q.from(ProductEntity.class);

			if (relationships!=null && relationships.equals("i")) {
				o.fetch("images", JoinType.LEFT);
			} else if (relationships!=null && relationships.equals("p")) {
				o.fetch("parentProduct", JoinType.LEFT);
			} else if (relationships!=null && relationships.equals("ip")) {
				o.fetch("images", JoinType.LEFT);
				o.fetch("parentProduct", JoinType.LEFT);
			}	

			q.select(o);
			q.where(cb.equal(o.get("id"), id));

			ProductEntity p = (ProductEntity)getEntityManager().createQuery(q).getSingleResult();	
			LOGGER.info("ProductRepository.get: return "+p);
			return p;

		} catch (AppException e) {
			LOGGER.severe("ProductRepository.get AppException: "+e.getMessage());
			throw e;
		} catch (Exception e) {
			LOGGER.severe("ProductRepository.get Exception: "+e.getMessage());
			throw AppBeanMessages.PERSISTENCE_ERROR.create(e, e.getMessage());
		}
	}


	public List<ProductEntity> getChildProducts(Long id) {
		try{
			LOGGER.info("ProductRepository.getChildProducts: "+id);
			Query query = getEntityManager().createNamedQuery("ProductEntity.getChildProducts");
			query.setParameter("id", id);
			List<ProductEntity> childProducts = (List<ProductEntity>)query.getResultList( );
			LOGGER.info("ProductRepository.getChildProducts: return "+childProducts);
			return childProducts;
		} catch (AppException e) {
			LOGGER.severe("ProductRepository.getChildProducts AppException: "+e.getMessage());
			throw e;
		} catch (Exception e) {
			LOGGER.severe("ProductRepository.getChildProducts Exception: "+e.getMessage());
			throw AppBeanMessages.PERSISTENCE_ERROR.create(e, e.getMessage());
		}
	}


	public List<ImageEntity> getImages(Long id) {
		try{
			LOGGER.info("ProductRepository.getImages: "+id);
			Query query = getEntityManager().createNamedQuery("ProductEntity.getImages");
			query.setParameter("id", id);
			List<ImageEntity> list = (List<ImageEntity>)query.getResultList( );
			LOGGER.info("ProductRepository.getImages: return "+list);
			return list;
		} catch (AppException e) {
			LOGGER.severe("ProductRepository.getImages AppException: "+e.getMessage());
			throw e;
		} catch (Exception e) {
			LOGGER.severe("ProductRepository.getImages Exception: "+e.getMessage());
			throw AppBeanMessages.PERSISTENCE_ERROR.create(e, e.getMessage());
		}
	}
}

