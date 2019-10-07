package com.demo.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Image Entity
 * 
 * @author rogerio.baldini
 */
@Entity
@Table(name="IMAGE")
@SequenceGenerator(name = "IMAGE_ID_GENERATOR", sequenceName = "SE_IMAGE", allocationSize = 1)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ImageEntity implements IEntity<Long>{

	private static final long serialVersionUID = 867075439636862292L;
	
	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IMAGE_ID_GENERATOR")
	private Long id;
	
	@NotNull 
	@Size(max = 10)
	@Column
	private String type;

    @ManyToOne (targetEntity = ProductEntity.class)  
    @JoinColumn(name="PRODUCT_ID", referencedColumnName="ID")
    @XmlTransient
    private ProductEntity product;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}

			

}
