package com.demo.app.model.service.product;

import java.util.List;

import javax.ejb.Local;

import com.demo.app.entity.ImageEntity;
import com.demo.app.entity.ProductEntity;
import com.demo.app.model.service.IEntityService;

/**
 * Product service interface 
 * 
 * @author rogerio.baldini
 *
 */

@Local
public interface IProductService  extends IEntityService<Long, ProductEntity>{

	List<ProductEntity> retrieveAll(String relationships);

	ProductEntity get(Long id, String relationships);

	List<ProductEntity> getChildProducts(Long id);
   
	List<ImageEntity>  getImages(Long id);

}
