package com.retail.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
/**
 * 
 * @author muthu
 *
 */
@Entity
@Table(name = "CustomerOrderDetails")
public class CustomerOrderDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8101289733138619483L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;
	private Long customerId;
	private Long productId;
	@Column(name="order_date")
	private Timestamp orderDate;
	@Column(name="shipping_date")
	private Timestamp shippingDate;
	private Integer quantity;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "productId", insertable = false, updatable = false, foreignKey=@ForeignKey(name = "fk_product_id"))
	private Product products;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customerId", insertable = false, updatable = false, foreignKey=@ForeignKey(name = "fk_customer_id"))
	private Customer customers;


	public CustomerOrderDetails() {
		// TODO Auto-generated constructor stub
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getProductid() {
		return productId;
	}

	public void setProductid(Long productid) {
		this.productId = productid;
	}

	public Timestamp getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	public Timestamp getShippingDate() {
		return shippingDate;
	}

	public void setShippingDate(Timestamp shippingDate) {
		this.shippingDate = shippingDate;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Product getProducts() {
		return this.products;
	}
	
	public void setProducts(Product products) {
		this.products = products;
	}

	public Customer getCustomers() {
		return customers;
	}

	public void setCustomers(Customer customers) {
		this.customers = customers;
	}
	
}
