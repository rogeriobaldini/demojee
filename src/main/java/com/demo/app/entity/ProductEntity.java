package com.demo.app.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Product Entity
 * 
 * @author rogerio.baldini
 */
@Entity
@Table(name="PRODUCT")
@SequenceGenerator(name = "PRODUCT_ID_GENERATOR", sequenceName = "SE_PRODUCT", allocationSize = 1)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

@NamedQueries({
    @NamedQuery(name="ProductEntity.retrieveAll", query="Select distinct p from ProductEntity p"),
    @NamedQuery(name="ProductEntity.retrieveAllI", query="Select distinct p from ProductEntity p LEFT OUTER JOIN p.images i"),
    @NamedQuery(name="ProductEntity.retrieveAllP", query="Select distinct p from ProductEntity p LEFT OUTER JOIN p.parentProduct pp"),
    @NamedQuery(name="ProductEntity.retrieveAllIP", query="Select distinct  p from ProductEntity p LEFT OUTER JOIN p.images i LEFT OUTER JOIN p.parentProduct pp"),
    @NamedQuery(name="ProductEntity.getChildProducts", query="Select distinct p from ProductEntity p where p.parentProduct.id = :id"),
    @NamedQuery(name="ProductEntity.getImages", query="Select distinct p.images from ProductEntity p LEFT OUTER JOIN p.images i where p.id = :id")
}) 



public class ProductEntity implements IEntity<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 867075439636862292L;
	

	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCT_ID_GENERATOR")
	private Long id;
	
	@NotNull 
	@Size(min=5, max = 40)
	@Column
	private String name;
	
	@Size(max = 255)
	@Column
	private String description;
	

    @ManyToOne (targetEntity = ProductEntity.class, fetch = FetchType.LAZY)    
    private ProductEntity parentProduct;

	
	@OneToMany (targetEntity = ImageEntity.class, fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="product")
	private List<ImageEntity> images;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public ProductEntity getParentProduct() {
		return parentProduct;
	}


	public void setParentProduct(ProductEntity parentProduct) {
		this.parentProduct = parentProduct;
	}


	public List<ImageEntity> getImages() {
		return images;
	}


	public void setImages(List<ImageEntity> images) {
		this.images = images;
	}
	
		

}
