package com.demo.app.model.service.product;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.demo.app.entity.ImageEntity;
import com.demo.app.entity.ProductEntity;
import com.demo.app.model.service.AbstractServiceEntity;
import com.demo.app.model.service.repository.IEntityRepository;
import com.demo.app.model.service.repository.product.ProductRepository;

/**
 * Product service interface 
 * 
 * @author rogerio.baldini
 *
 */
@Stateless
public class ProductServiceImpl extends AbstractServiceEntity<Long, ProductEntity> implements IProductService {

	private final static Logger LOGGER = Logger.getLogger(ProductServiceImpl.class.getName());	
	
	@Inject
	private ProductRepository productRepository;

	@Override
	protected IEntityRepository<Long, ProductEntity> getEntityRepository() {
		return productRepository;
	}

	@Override
	public List<ProductEntity> retrieveAll(String relationships) {	
		LOGGER.info("ProductServiceImpl.retrieveAll");
		List<ProductEntity> productList = productRepository.retrieveAll(relationships);
		
		// Mounting DTO in the same object
		List<ProductEntity> newProductList = new ArrayList<ProductEntity>();
		for(ProductEntity p : productList) {
			ProductEntity newP = getProductDTO(relationships, p);	
			newProductList.add(newP);
		}
		LOGGER.info("ProductServiceImpl.retrieveAll: "+newProductList);
		return newProductList;
	}

	@Override
	public ProductEntity get(Long id) {
		LOGGER.info("ProductServiceImpl.get");
		return get(id, "");
	}
	
	@Override
	public ProductEntity get(Long id, String relationships) {
		LOGGER.info("ProductServiceImpl.get(relationships)");
		ProductEntity p = productRepository.get(id, relationships);
		if (p!=null) { 
			ProductEntity newP = getProductDTO(relationships, p);
			LOGGER.info("ProductServiceImpl.get(relationships): "+newP);
			return newP;
		} else {
			LOGGER.info("ProductServiceImpl.get(relationships): null");
			return null;
		}
		
	}
	
	
	protected ProductEntity getProductDTO(String relationships, ProductEntity p) {
		LOGGER.info("ProductServiceImpl.getProductDTO");
		ProductEntity newP = new ProductEntity();
		newP.setId(p.getId());
		newP.setName(p.getName());
		newP.setDescription(p.getDescription());
		
		if (relationships!=null && relationships.equals("i")) {
			newP.setImages(p.getImages());
		} else if (relationships!=null && relationships.equals("p")) {
			newP.setParentProduct(p.getParentProduct());
		} else if (relationships!=null && relationships.equals("ip")) {
			newP.setImages(p.getImages());
			newP.setParentProduct(p.getParentProduct());
		}
		return newP;
	}

	@Override
	public List<ProductEntity> getChildProducts(Long id) {
		LOGGER.info("ProductServiceImpl.getChildProducts");
		List<ProductEntity> childProducts = productRepository.getChildProducts(id);
		// Mounting DTO in the same object
		List<ProductEntity> newProductList = new ArrayList<ProductEntity>();
		for(ProductEntity p : childProducts) {
			ProductEntity newP = getProductDTO("", p);	
			newProductList.add(newP);
		}
		LOGGER.info("ProductServiceImpl.getChildProducts: "+newProductList);
		return newProductList;
	}

	@Override
	public List<ImageEntity> getImages(Long id) {
		LOGGER.info("ProductServiceImpl.getImages");
		List<ImageEntity> listImages = productRepository.getImages(id);
		LOGGER.info("ProductServiceImpl.getImages: "+listImages);
		return listImages;
	}
	
}
