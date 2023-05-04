package com.retail.service;

import java.util.List;
import java.util.Map;

import com.retail.exception.ResourceNotFoundException;
import com.retail.model.CustomerOrderDetails;
import com.retail.model.CustomerSpendingAndRewardGroup;

public interface OrderDetailsService {
	public List<CustomerOrderDetails> findByCustomerId(Long customerId) throws ResourceNotFoundException;
	public Map<Long, List<CustomerSpendingAndRewardGroup>> getCustomerOrderDetailsGroupByCustomerId() throws ResourceNotFoundException;
	public CustomerSpendingAndRewardGroup getCustomerOrderDetailsByCustomerId(Long customerId)throws ResourceNotFoundException;
}
