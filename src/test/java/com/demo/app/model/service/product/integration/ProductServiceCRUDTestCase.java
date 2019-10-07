package com.demo.app.model.service.product.integration;


import java.util.List;

import javax.ejb.EJB;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import com.demo.app.entity.ProductEntity;
import com.demo.app.model.service.AppAbstractArquillianTestCase;
import com.demo.app.model.service.product.IProductService;


@RunWith(Arquillian.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductServiceCRUDTestCase extends AppAbstractArquillianTestCase {

	@EJB IProductService productService;


	@Test
	public void test1Create() throws Exception
	{

		ProductEntity p = new ProductEntity();
		p.setName("New Product");
		p.setDescription("New Product description");
		productService.create(p);

		ProductEntity pGet = productService.get(4L);

		Assert.assertEquals("Id must be equal", (long)p.getId(), (long)pGet.getId());		
		Assert.assertEquals("Name must be equal", p.getName(), pGet.getName());		
		Assert.assertEquals("Description must be equal", p.getDescription(), pGet.getDescription());		

	}

	@Test
	public void test1CreateException() throws Exception
	{

		try {
			ProductEntity p = new ProductEntity();
			p.setName("New Product testing more then fourty characters product name");
			p.setDescription("New Product description");
			productService.create(p);
			Assert.fail("Accepting more then 40 characters in product name");
		} catch (Exception e) {
			Assert.assertEquals("Exception doesn't match", javax.validation.ConstraintViolationException.class, e.getCause().getClass());
		}

	}


	@Test
	public void test2Retrieve() throws Exception
	{
		List<ProductEntity> productList = productService.retrieve();
		Assert.assertEquals("Product list size not corret", productList.size(), 4);	
	}



	@Test
	public void test3Update() throws Exception
	{

		ProductEntity p = new ProductEntity();
		p.setId(4L);
		p.setName("New Product updated");
		p.setDescription("New Product description updated");
		productService.update(p);

		ProductEntity pGet = productService.get(4L);

		Assert.assertEquals("Id must be equal", (long)p.getId(), (long)pGet.getId());		
		Assert.assertEquals("Name must be equal", p.getName(), pGet.getName());		
		Assert.assertEquals("Description must be equal", p.getDescription(), pGet.getDescription());			
	}

	@Test
	public void test4Delete() throws Exception
	{

		ProductEntity p = new ProductEntity();
		p.setId(4L);
		productService.delete(p);

		try {
			productService.get(4L);
			Assert.fail("Product should not be found");
		} catch (Exception e) {
			Assert.assertEquals("Exception message doens't match ", "No entity found for query", e.getCause().getMessage());
		}		

	}

}


