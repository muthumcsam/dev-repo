package com.retail.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
/**
 * 
 * @author muthu
 *
 */
@Entity
@Table(name="product")
public class Product implements Serializable{

	private static final long serialVersionUID = -8847057727612507862L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;
	@Column(name="product_name")
	private String productName;
	private String category;
	private Double retailPrice;
	private Timestamp createdDate;
	/*
	 * @OneToMany(targetEntity = CustomerOrderDetails.class,fetch = FetchType.EAGER,
	 * mappedBy = "productId") private Set<CustomerOrderDetails>
	 * customerOrderDetails;
	 */

	public Product() {
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * public Set<CustomerOrderDetails> getCustomerOrderDetails() { return
	 * this.customerOrderDetails; }
	 * 
	 * public void setCustomerOrderDetails(Set<CustomerOrderDetails>
	 * customerOrderDetails) { this.customerOrderDetails = customerOrderDetails; }
	 */

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Double getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(Double retailPrice) {
		this.retailPrice = retailPrice;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	
}
