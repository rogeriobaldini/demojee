package com.demo.app.rest.product;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import com.demo.app.entity.ImageEntity;
import com.demo.app.entity.ProductEntity;
import com.demo.app.exception.AppException;
import com.demo.app.model.service.IEntityService;
import com.demo.app.model.service.product.IProductService;
import com.demo.app.rest.AbstractRest;

/**
 * Class Rest to provide product rest operations
 * 
 * @author rogerio.baldini
 *
 */
@Path("/product")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class ProductRest extends AbstractRest<Long, ProductEntity>{
	
	private final static Logger LOGGER = Logger.getLogger(ProductRest.class.getName());
	
	@Inject
	private IProductService productService;
	
	@Override
	protected IEntityService<Long, ProductEntity> getEntityService() {
		return productService;
	}

	@GET
	@Path("/all")
	public List<ProductEntity> getAllProducts() throws AppException{
		LOGGER.info("ProductRest.getAllProducts");
		List<ProductEntity> listProducts = productService.retrieveAll(null);
		handleNoContent(listProducts);
		LOGGER.info("ProductRest.getAllProducts: "+listProducts);
		return listProducts;
	}

	
	@GET
	@Path("/all/{relationships}")
	public List<ProductEntity> getAllProducts(@PathParam("relationships") String relationships) throws AppException{
		LOGGER.info("ProductRest.getAllProducts{relationships}");
		List<ProductEntity> listProducts = productService.retrieveAll(relationships);
		handleNoContent(listProducts);
		LOGGER.info("ProductRest.getAllProducts{relationships}: "+listProducts);
		return listProducts;
	}

	@GET
	@Path("/{id}/{relationships}")
	public ProductEntity get( @PathParam("id") Long entityId, @PathParam("relationships") String relationships) throws AppException {
		LOGGER.info("ProductRest.get{relationships}");
		ProductEntity p=  productService.get(entityId, relationships);
		LOGGER.info("ProductRest.get{relationships}: "+p);
		return p;
	}
	
	@GET
	@Path("/childProducts/{id}")
	public List<ProductEntity> getChildProducts(@PathParam("id") Long entityId) throws AppException {
		LOGGER.info("ProductRest.getChildProducts");
		List<ProductEntity> listProducts=  productService.getChildProducts(entityId);
		handleNoContent(listProducts);	
		LOGGER.info("ProductRest.getChildProducts: "+listProducts);
		return listProducts;
	}
	
	
	@GET
	@Path("/images/{id}")
	public List<ImageEntity> getImages(@PathParam("id") Long entityId) throws AppException {
		LOGGER.info("ProductRest.getImages");
		List<ImageEntity> images=  productService.getImages(entityId);
		if (images==null) {
			LOGGER.warning("ProductRest.getImages: SC_NO_CONTENT");
			new WebApplicationException(HttpServletResponse.SC_NO_CONTENT);		
		}		
		LOGGER.info("ProductRest.getImages: "+images);
		return images;
	}
}
