package com.retail.model;

import java.io.Serializable;
import java.time.Month;
/**
 * Product and CustomerOrderDetails Join
 * @author muthu
 *
 */
public class CustomerSpendingAndRewardGroup implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1750126678694803970L;
	private Long  customerId;
    private Double totalCost; 
    private String customerName;
    private String monthOfYear;
    private Long rewardPoints;
    

	public CustomerSpendingAndRewardGroup() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 * @param customerid
	 * @param totalcost
	 * @param customername
	 */
	public CustomerSpendingAndRewardGroup(Long  customerId,Double totalCost,String customerName,String monthOfYear,Long rewardPoints) {
		this.customerId = customerId;
		this.totalCost = totalCost;
		this.customerName = customerName;
		this.monthOfYear =  monthOfYear;
		this.rewardPoints = rewardPoints;
	}

	public String getMonthOfyear() {
		return monthOfYear;
	}
	public void setMonthOfyear(String monthOfYear) {
		this.monthOfYear = monthOfYear;
	}
	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Long getRewardPoints() {
		return rewardPoints;
	}
	public void setRewardPoints(Long rewardPoints) {
		this.rewardPoints = rewardPoints;
	}
}
