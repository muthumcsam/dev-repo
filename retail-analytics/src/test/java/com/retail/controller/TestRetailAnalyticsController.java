package com.retail.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.retail.model.CustomerOrderDetails;
import com.retail.model.CustomerSpendingAndRewardGroup;
import com.retail.repo.OrderDetailsRepository;
import com.retail.service.OrderDetailsService;

/**
 * Test class for the RetailAnalyticsController
 * 
 * @author muthu
 *
 */
@WebMvcTest(value = RetailAnalyticsController.class, excludeAutoConfiguration = { SecurityAutoConfiguration.class })
public class TestRetailAnalyticsController {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	@Qualifier("OrderDetailsServiceImpl")
	private OrderDetailsService orderDetailsService;

	@MockBean
	private OrderDetailsRepository repo;

	/**
	 * Test for RetailAnalyticsController.getCustomerOrderDetailsGroupByCustomerId()
	 * 
	 * @throws Exception
	 */
	@Test
	@DisplayName("JUnit test for RetailAnalyticsController.getGustomerOrderDetailsGroupByCustomerId(...) method")
	public void getCustomerOrderDetailsGroupByCustomerId_Test() throws Exception {
		Map<Long, List<CustomerSpendingAndRewardGroup>> map = null;
		CustomerSpendingAndRewardGroup customerSpending = new CustomerSpendingAndRewardGroup();
		customerSpending.setCustomerId(101L);
		customerSpending.setCustomerId(1001L);
		customerSpending.setMonthOfyear("Feb");
		customerSpending.setRewardPoints(5000L);
		customerSpending.setTotalCost(10001.00);
		List<CustomerSpendingAndRewardGroup> list = new ArrayList<>();
		list.add(customerSpending);
		map = new HashMap<>();
		map.put(100L, list);
		when(orderDetailsService.getCustomerOrderDetailsGroupByCustomerId()).thenReturn(map);
		ResultActions actions = this.mockMvc.perform(get("/retail/find/montly-rewards"));
		actions = actions.andDo(print());
		actions = actions.andExpect(status().isOk());
		actions = actions.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	/**
	 * Test for RetailAnalyticsController.getOrderDetailsByCustomerId(customerId)
	 * 
	 * @throws Exception
	 */
	@Test
	@DisplayName("JUnit test for RetailAnalyticsController.getOrderDetailsByCustomerId(...) method")
	public void getOrderDetailsByCustomerId_Test() throws Exception {
		CustomerOrderDetails customerOrderDetails = new CustomerOrderDetails();
		customerOrderDetails.setCustomerId(101L);
		customerOrderDetails.setProductid(2001L);
		customerOrderDetails.setQuantity(100);
		List<CustomerOrderDetails> list = new ArrayList<>();
		list.add(customerOrderDetails);
		when(orderDetailsService.findByCustomerId(1001L)).thenReturn(list);
		ResultActions actions = this.mockMvc.perform(get("/retail/find-customer-orders/1001"));
		actions = actions.andDo(print());
		actions = actions.andExpect(status().isOk());
		actions = actions.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}
}