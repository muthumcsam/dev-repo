package com.retail.repo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.retail.exception.OrderDetailsRepoDaoException;
import com.retail.model.CustomerOrderDetails;
import com.retail.model.CustomerSpendingAndRewardGroup;

/**
 * OrderDetailsRepository DB data load test
 * 
 * @author muthu
 *
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TestOrderDetailsRepository {

	@Autowired
	private OrderDetailsRepository orderDetailsRepository;

	private CustomerOrderDetails orderDetails;

	@BeforeEach
	public void setup() {
		orderDetails = new CustomerOrderDetails();
		orderDetails.setCustomerId(101L);
		orderDetails.setProductid(201L);
		orderDetails.setQuantity(5);
	}

	/**
	 * Test for findByCustomerId(customerId)
	 */
	@Test
	@DisplayName("JUnit test for OrderDetailsRepository.findByCustomerId(...) method")
	public void customerOrderDetails_findByCustomerId_Test() throws OrderDetailsRepoDaoException{
		List<CustomerOrderDetails> orderDetails = orderDetailsRepository.findByCustomerId(5L);
		assertThat(orderDetails).isNotNull();
		assertThat(orderDetails.size()).isGreaterThan(0);
	}

	/**
	 * Test for getCustomerOrderDetailsGroupByCustomerId()
	 */
	@Test
	@DisplayName("JUnit test for OrderDetailsRepository.getCustomerOrderDetailsGroupByCustomerId(...) method")
	public void customerOrderDetails_getCustomerOrderDetailsGroupByCustomerId_Test() throws OrderDetailsRepoDaoException{
		List<CustomerSpendingAndRewardGroup> orderDetails = orderDetailsRepository
				.getCustomerOrderDetailsGroupByCustomerId();
		assertThat(orderDetails).isNotNull();
		assertThat(orderDetails.size()).isGreaterThan(0);
	}
}