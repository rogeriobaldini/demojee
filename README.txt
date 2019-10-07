README

Comments:
=========

- EJB3 Stateless to implement service
- JPA to persist objects in the database (Hibernate used in the application server)
- Using embedded WildFly 10.1.0.Final to run application and run automated integration tests
- Using H2 as embedded in-memory database both on the aaplication server and on the integration tests
- Database creation, tables creation and initial data load being done by application  
- Initial data load is in file insert.sql
	insert into PRODUCT (id, name, description, parentProduct_id) values ('1', 'Notebook', 'Notebook Dell Modelo Vostro 1100', null);
	insert into PRODUCT (id, name, description, parentProduct_id) values ('2', 'Teclado', 'Teclado Dell Modelo Vostro 1100',  '1');
	insert into PRODUCT (id, name, description, parentProduct_id) values ('3', 'Mouse', 'Mouse Dell Modelo Vostro 1100',  '1');
	insert into IMAGE (id, type, product_id) values ('1', 'jpeg', '1');
	insert into IMAGE (id, type, product_id) values ('2', 'png', '1');


Commands:
=========

To run tests:  mvn test

To start application:  mvn -Dmaven.test.skip=true wildfly:run


Application Tests:	
==================

1° Testing CRUD

	CREATE
		curl -sD - -X POST -H "Content-Type: application/json" "http://localhost:8080/demojee/rest/product/create" -d "{\"name\":\"New Product\",\"description\":\"New product description\"}"

	RETRIEVE	
		curl -sD - -X GET -H "Content-Type: application/json" "http://localhost:8080/demojee/rest/product" 
	
	UPDATE
		curl -sD - -X PUT -H "Content-Type: application/json" "http://localhost:8080/demojee/rest/product/4" -d "{\"id\":\"4\",\"name\":\"New Product updated\",\"description\":\"New Product description updated\"}"
	
	DELETE
		curl -sD - -X DELETE -H "Content-Type: application/json" "http://localhost:8080/demojee/rest/product/4" -d "{\"id\":\"4\"}"


2° Testing other operations

	a) Get all products excluding relationships (child products, images)

		curl -sD - -X GET -H "Content-Type: application/json" "http://localhost:8080/demojee/rest/product/all" 

	b) Get all products including specified relationships (child product and/or images)

		(only images)
		curl -sD - -X GET -H "Content-Type: application/json" "http://localhost:8080/demojee/rest/product/all/i"    
		
		(only parent Product) 
		curl -sD - -X GET -H "Content-Type: application/json" "http://localhost:8080/demojee/rest/product/all/p"	
		
		(imagens and parent Product)
		curl -sD - -X GET -H "Content-Type: application/json" "http://localhost:8080/demojee/rest/product/all/ip" 	
	
	c) Same as 1 using specific product identity

		curl -sD - -X GET -H "Content-Type: application/json" "http://localhost:8080/demojee/rest/product/1"
		
	d) Same as 2 using specific product identity

		curl -sD - -X GET -H "Content-Type: application/json" "http://localhost:8080/demojee/rest/product/1/i"
		
		curl -sD - -X GET -H "Content-Type: application/json" "http://localhost:8080/demojee/rest/product/2/p"	
		
		curl -sD - -X GET -H "Content-Type: application/json" "http://localhost:8080/demojee/rest/product/2/ip"
	
	e) Get set of child products for specific product

		curl -sD - -X GET -H "Content-Type: application/json" "http://localhost:8080/demojee/rest/product/childProducts/1"
	
	f) Get set of images for specific product

		curl -sD - -X GET -H "Content-Type: application/json" "http://localhost:8080/demojee/rest/product/images/1"
	
	
