package com.retail.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.retail.exception.OrderDetailsRepoDaoException;
import com.retail.model.CustomerOrderDetails;
import com.retail.model.CustomerSpendingAndRewardGroup;
import com.retail.model.Product;
import com.retail.repo.OrderDetailsRepository;
import com.retail.util.RewardConfigUtils;

/**
 * Test for the OrderDetailsService methods
 * 
 * @author muthu
 *
 */
public class TestOrderDetailsServiceImpl {

	@InjectMocks
	private OrderDetailsServiceImpl orderDetailsServiceImpl;
	@Mock
	private RewardConfigUtils configUtils;

	@Mock
	private OrderDetailsRepository orderDetailsRepository;

	private CustomerOrderDetails orderDetails;
	private CustomerSpendingAndRewardGroup customerSpendingAndRewardGroup;
	private List<CustomerOrderDetails> list;
	private List<CustomerSpendingAndRewardGroup> customerSpending;
	private Product product;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		product = new Product();
		product.setProductId(101L);
		product.setProductName("LAPTOP");
		product.setRetailPrice(100.0);
		orderDetails = new CustomerOrderDetails();
		orderDetails.setProducts(product);
		orderDetails.setCustomerId(101L);
		orderDetails.setProductid(201L);
		orderDetails.setQuantity(5);
		list = new ArrayList<>();
		list.add(orderDetails);

		customerSpendingAndRewardGroup = new CustomerSpendingAndRewardGroup();
		customerSpendingAndRewardGroup.setCustomerId(101L);
		customerSpendingAndRewardGroup.setMonthOfyear("November");
		customerSpendingAndRewardGroup.setTotalCost(10001.00);
		customerSpending = new ArrayList<>();
		customerSpending.add(customerSpendingAndRewardGroup);
	}

	@DisplayName("JUnit test for findByCustomerId(...) method")
    @Test
    public void findByCustomerId_test() throws OrderDetailsRepoDaoException{
        when(orderDetailsRepository.findByCustomerId(any())).thenReturn(list);
        List<CustomerOrderDetails>  savedEmployee = orderDetailsServiceImpl.findByCustomerId(5L);
        assertThat(savedEmployee).isNotNull();
        assertEquals(5, savedEmployee.get(0).getQuantity());
    }

	/**
     *  Test for the scenarios:
     *   1) Every customer receives 2 points for every dollar spent over $100 in each transaction, 
     *   2) and 1 point for every dollar spent over $50 in each transaction
     *   Output : Reward calculation at the service level
     */
    @DisplayName("JUnit test for getCustomerOrderDetailsGroupByCustomerId(...) method")
    @Test
    public void getCustomerOrderDetailsGroupByCustomerId_test() throws OrderDetailsRepoDaoException{
    	when(configUtils.getSilverRewardBonusPoints()).thenReturn(50);
    	when(configUtils.getGoldRewardBonusPoints()).thenReturn(100);
    	when(orderDetailsRepository.getCustomerOrderDetailsGroupByCustomerId())
            .thenReturn(customerSpending);
    	Map<Long,List<CustomerSpendingAndRewardGroup> > customerAward = orderDetailsServiceImpl.getCustomerOrderDetailsGroupByCustomerId();
    	System.out.println(customerAward.get(101L).get(0).getRewardPoints());
    	assertThat(customerAward).isNotNull();
    	assertEquals(19852, customerAward.get(101L).get(0).getRewardPoints());
    }

}
