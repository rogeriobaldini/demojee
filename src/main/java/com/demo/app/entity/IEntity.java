package com.demo.app.entity;

import java.io.Serializable;

/**
 * 
 * Interface to IOC for REST entities
 * 
 * @author rogerio.baldini
 */
public interface IEntity<PK extends Serializable> extends Serializable {

	PK getId();

	void setId(PK id);
	
}