package com.demo.app.model.service.product.integration;


import java.util.List;

import javax.ejb.EJB;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import com.demo.app.entity.ImageEntity;
import com.demo.app.entity.ProductEntity;
import com.demo.app.model.service.AppAbstractArquillianTestCase;
import com.demo.app.model.service.product.IProductService;


@RunWith(Arquillian.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductServiceOperationsTestCase extends AppAbstractArquillianTestCase {

	@EJB IProductService productService;

	// Item a)
	@Test
	public void test01RetrieveAll() throws Exception
	{

		List<ProductEntity> productList = productService.retrieveAll(null);
		Assert.assertEquals(productList.size(), 3);
		
		for(ProductEntity p : productList) {
			Assert.assertNull("Images list must be null", p.getImages());
			Assert.assertNull("Parent Product must be null", p.getParentProduct());
		}

	}
	
	// Item b)
	@Test
	public void test02RetrieveAllI() throws Exception
	{

		List<ProductEntity> productList = productService.retrieveAll("i");
		Assert.assertEquals(3, productList.size());
		
		for(ProductEntity p : productList) {
			Assert.assertNotNull("Images list must be not null", p.getImages());
			Assert.assertNull("Parent Product must be null", p.getParentProduct());
		}

	}

	// Item b)
	@Test
	public void test03RetrieveAllP() throws Exception
	{

		List<ProductEntity> productList = productService.retrieveAll("p");
		Assert.assertEquals("Product list size not corret", 3, productList.size());
		
		for(ProductEntity p : productList) {
			Assert.assertNull("Images list must be null", p.getImages());
			if (!p.getId().equals(1L)) {
				Assert.assertNotNull("Parent Product must be not null", p.getParentProduct());
			}
		}

	}
	
	// Item b)
	@Test
	public void test04RetrieveAllIP() throws Exception
	{

		List<ProductEntity> productList = productService.retrieveAll("ip");
		Assert.assertEquals("Product list size not corret", 3, productList.size());
		
		for(ProductEntity p : productList) {
			Assert.assertNotNull("Images list must be not null",p.getImages());
			if (!p.getId().equals(1L)) {			
				Assert.assertNotNull("Parent Product must be not null", p.getParentProduct());
			}
		}

	}
	
	// Item c)
	@Test
	public void test05RetrieveOne() throws Exception
	{
		ProductEntity p = productService.get(1L);

		Assert.assertEquals("Id must be equal", (long)p.getId(), 1L);		
		Assert.assertEquals("Name must be equal", p.getName(), "Notebook");		
		Assert.assertEquals("Description must be equal", p.getDescription(), "Notebook Dell Modelo Vostro 1100");	
		Assert.assertNull("Images list must be null", p.getImages());
		Assert.assertNull("Parent Product must be null", p.getParentProduct());
	}
	
	// Item d)
	@Test
	public void test06RetrieveOneI() throws Exception
	{
		ProductEntity p = productService.get(1L, "i");

		Assert.assertEquals("Id must be equal", (long)p.getId(), 1L);		
		Assert.assertEquals("Name must be equal", p.getName(), "Notebook");		
		Assert.assertEquals("Description must be equal", p.getDescription(), "Notebook Dell Modelo Vostro 1100");	
		Assert.assertNotNull("Images list must be not null",p.getImages());
		Assert.assertNull("Parent Product must be null", p.getParentProduct());
	}
	
	// Item d)
	@Test
	public void test07RetrieveOneP() throws Exception
	{
		ProductEntity p = productService.get(2L, "p");

		Assert.assertEquals("Id must be equal", (long)p.getId(), 2L);		
		Assert.assertEquals("Name must be equal", p.getName(), "Teclado");		
		Assert.assertEquals("Description must be equal", p.getDescription(), "Teclado Dell Modelo Vostro 1100");	
		Assert.assertNull("Images list must be null", p.getImages());
		Assert.assertNotNull("Parent Product must be not null", p.getParentProduct());
	}	
	
	// Item d)
	@Test
	public void test08RetrieveOneIP() throws Exception
	{
		ProductEntity p = productService.get(2L, "ip");

		Assert.assertEquals("Id must be equal", (long)p.getId(), 2L);		
		Assert.assertEquals("Name must be equal", p.getName(), "Teclado");		
		Assert.assertEquals("Description must be equal", p.getDescription(), "Teclado Dell Modelo Vostro 1100");	
		Assert.assertNotNull("Images list must be not null",p.getImages());
		Assert.assertNotNull("Parent Product must be not null", p.getParentProduct());
	}

	// Item e)
	@Test
	public void test09GetChildProducts() throws Exception
	{
		List<ProductEntity> childProducts = productService.getChildProducts(1L);

		Assert.assertEquals("Child Products list size not corret", childProducts.size(), 2L);		

	}

	// Item e)
	@Test
	public void test10GetImages() throws Exception
	{
		List<ImageEntity> images = productService.getImages(1L);
		Assert.assertEquals("Image list size not corret", images.size(), 2L);		

		images = productService.getImages(2L);
		Assert.assertEquals("Product list size must be empty", images.size(), 0L);	
	}


}


