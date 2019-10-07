package com.demo.app.model.service;


import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;

import com.demo.app.entity.ProductEntity;
import com.demo.app.exception.AppException;
import com.demo.app.messages.AppBeanMessages;
import com.demo.app.model.service.product.IProductService;
import com.demo.app.model.service.repository.IEntityRepository;
import com.demo.app.model.service.repository.product.ProductRepository;
import com.demo.app.rest.IEntityRest;
import com.demo.app.rest.product.ProductRest;

/**
 * Arquillian deployment configuration class
 * 
 * @author rogerio.baldini
 *
 */
public abstract class AppAbstractArquillianTestCase  {
	

	@Deployment
	public static WebArchive createTestArchive() {
		WebArchive j = ShrinkWrap.create(WebArchive.class);
		
		j.addPackages(true, ProductEntity.class.getPackage());
		j.addPackages(true, AppException.class.getPackage());
		j.addPackages(true, AppBeanMessages.class.getPackage());
		j.addPackages(true, IEntityService.class.getPackage());
		j.addPackages(true, IProductService.class.getPackage());
		j.addPackages(true, IEntityRepository.class.getPackage());
		j.addPackages(true, ProductRepository.class.getPackage());
		j.addPackages(true, IEntityRest.class.getPackage());
		j.addPackages(true, ProductRest.class.getPackage());

		j.addAsResource("META-INF/persistence.xml", ArchivePaths.create("META-INF/persistence.xml"));
		j.addAsResource("META-INF/beans.xml", ArchivePaths.create("META-INF/beans.xml"));
		j.addAsResource("META-INF/orm.xml", ArchivePaths.create("META-INF/orm.xml"));
		j.addAsResource("insert.sql", ArchivePaths.create("insert.sql"));

		return j;
	}
		
}


