package com.retail.model;

import java.util.Set;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity
@Table(name = "customer")
public class Customer {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8847057727612507562L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="customer_id")
	private Long customerId;
	@Column(name="customer_name")
	private String customerName;
	private String address;
	private String email;
	private Long phone;

	/*
	 * @OneToMany(targetEntity = CustomerOrderDetails.class,fetch = FetchType.EAGER,
	 * mappedBy = "customerId") private Set<CustomerOrderDetails>
	 * customerOrderDetails;
	 */
	public Customer() {
		// TODO Auto-generated constructor stub
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getPhone() {
		return phone;
	}
	public void setPhone(Long phone) {
		this.phone = phone;
	}

	/*
	 * public Set<CustomerOrderDetails> getCustomerOrderDetails() { return
	 * customerOrderDetails; } public void
	 * setCustomerOrderDetails(Set<CustomerOrderDetails> customerOrderDetails) {
	 * this.customerOrderDetails = customerOrderDetails; }
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
