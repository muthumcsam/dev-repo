package com.retail.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.retail.exception.OrderDetailsRepoDaoException;
import com.retail.model.CustomerOrderDetails;
import com.retail.model.CustomerSpendingAndRewardGroup;

import jakarta.transaction.Transactional;

/**
 * DAO Repository to handle the ORM mapping with the provided DB
 * 
 * @author muthu
 * 
 */
@Repository("orderDetailsRepository")
@Transactional
public interface OrderDetailsRepository extends CrudRepository<CustomerOrderDetails, Long> {
	/**
	 * DAO Repository for findByCustomerId(...) - All order transactions for the
	 * provided customer.
	 * 
	 * @param customerId
	 * @return List<CustomerOrderDetails>
	 */
	public List<CustomerOrderDetails> findByCustomerId(Long customerId) throws OrderDetailsRepoDaoException;

	/**
	 * DAO Repository for getCustomerOrderDetailsGroupByCustomerId() group by
	 * monthly.
	 * 
	 * @return List<CustomerSpendingAndRewardGroup>
	 */
	@Query("select new com.retail.model.CustomerSpendingAndRewardGroup(" + " cusorder.customerId as customerId,"
			+ " monthName(cusorder.orderDate) monthOfYear," + " cusorder.customers.customerName customerName,"
			+ " sum(cusorder.quantity * cusorder.products.retailPrice) as totalCost )"
			+ " from CustomerOrderDetails  cusorder" + " group by cusorder.customerId,monthOfYear")
	public List<CustomerSpendingAndRewardGroup> getCustomerOrderDetailsGroupByCustomerId()
			throws OrderDetailsRepoDaoException;

	/**
	 * DAO Repository for getCustomerOrderDetailsByCustomerId(...) for a given
	 * customer
	 * 
	 * @return CustomerSpendingAndRewardGroup
	 */
	@Query("select new com.retail.model.CustomerSpendingAndRewardGroup(" + " cusorder.customerId as customerId,"
			+ " cusorder.customers.customerName customerName,"
			+ " sum(cusorder.quantity * cusorder.products.retailPrice) as totalCost )"
			+ " from CustomerOrderDetails  cusorder" + " where cusorder.customerId = :customerId"
			+ " group by cusorder.customerId")
	public CustomerSpendingAndRewardGroup getCustomerOrderDetailsByCustomerId(Long customerId)
			throws OrderDetailsRepoDaoException;
}
