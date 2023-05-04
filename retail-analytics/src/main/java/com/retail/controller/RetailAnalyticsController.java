package com.retail.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.retail.exception.OrderDetailsServiceException;
import com.retail.exception.ResourceNotFoundException;
import com.retail.model.CustomerOrderDetails;
import com.retail.model.CustomerSpendingAndRewardGroup;
import com.retail.repo.OrderDetailsRepository;
import com.retail.service.OrderDetailsService;
import com.retail.service.impl.OrderDetailsServiceImpl;
/**
 * Controller for Retail Analytics APIs
 * @author muthu
 *
 */
@RestController
@RequestMapping("/retail")
public class RetailAnalyticsController {
	
	@Autowired
	@Qualifier("OrderDetailsServiceImpl")
	OrderDetailsService orderDetailsService;
	@Autowired
	OrderDetailsRepository orderDetailsRepository;
	private static final Logger LOGGER = LoggerFactory.getLogger(RetailAnalyticsController.class);
	/**
	 * GET/ Rest API Service call for the uri - /find-customer-orders/{customerId}
	 * @param customerid 
	 * @return ResponseEntity<List<CustomerOrderDetails>>
	 */
	@RequestMapping(value="/find-customer-orders/{customerId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CustomerOrderDetails>> getOrderDetailsByCustomerId(@PathVariable("customerId") Long customerId) {
		LOGGER.info("<<==========================================================>>");
		LOGGER.info("	  Start-->/retail/find-customer-orders/"+customerId);
		LOGGER.info("<<==========================================================>>");
		if (customerId == null) {
			LOGGER.warn("Controller Warning: Missing customer Id ");
			throw new ResponseStatusException(
			           HttpStatus.NOT_FOUND, "Controller Warning: Missing customer Id");
		}
		List<CustomerOrderDetails> orderDetails = null;
		try {
			orderDetails = orderDetailsService.findByCustomerId(customerId);
			if (orderDetails != null) {
				LOGGER.warn("Successfully retrived!!!");
				return new ResponseEntity<>(orderDetails, HttpStatus.OK);
			}
			LOGGER.info("End-->/retail/find-customer-orders/"+customerId);
		}
		catch(ResourceNotFoundException osExp) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Controller Warning: Sorry, Unable to fetch customer details!!!");
		}
		return new ResponseEntity<>(orderDetails, HttpStatus.NO_CONTENT);
	}
	/**
	 * GET/ Rest API Service call for the uri - /find/montly-rewards
	 * @return ResponseEntity<>(orderDetails, HttpStatus.NO_CONTENT)
	 */
	@RequestMapping(value="/find/montly-rewards", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<Long, List<CustomerSpendingAndRewardGroup>>> findAllOrders() {
		LOGGER.info("<<===========================================================>>");
		LOGGER.info("	    Start-->/find/montly-rewards");
		LOGGER.info("<<===========================================================>>");
		Map<Long, List<CustomerSpendingAndRewardGroup>> orderDetails = null;
		try {
			orderDetails = orderDetailsService.getCustomerOrderDetailsGroupByCustomerId();
			if (orderDetails != null) {
				LOGGER.warn("Successfully retrieved for"+orderDetails.keySet()+" customers");
				return new ResponseEntity<>(orderDetails, HttpStatus.OK);
			}
			else {
				LOGGER.warn("There is no record for reward!!!");
			}
			LOGGER.info("End-->/find/montly-rewards");
		}
		catch(ResourceNotFoundException osExp) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Controller Warning: Sorry, Unable to fetch customer details!!!");
		}
		return new ResponseEntity<>(orderDetails, HttpStatus.NO_CONTENT);
	}
	
	/**
	 * GET/ Rest API Service call for the uri -/find/consumer-rewards/{customerId}
	 * @return ResponseEntity<>(orderDetails, HttpStatus.NO_CONTENT)
	 */
	@RequestMapping(value="/find/consumer-rewards/{customerId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CustomerSpendingAndRewardGroup> getCustomerOrderDetailsByCustomerId(@PathVariable("customerId") Long customerId) {
		LOGGER.info("<<===========================================================>>");
		LOGGER.info("	    Start-->/find/consumer-rewards/{customerId}");
		LOGGER.info("<<===========================================================>>");
		CustomerSpendingAndRewardGroup orderDetails = null;
		try {
			orderDetails = orderDetailsService.getCustomerOrderDetailsByCustomerId(customerId);
			if (orderDetails != null) {
				LOGGER.warn("Successfully retrieved for"+customerId+" customer");
				return new ResponseEntity<>(orderDetails, HttpStatus.OK);
			}
			else {
				LOGGER.warn("There is no record for reward!!!");
			}
			LOGGER.info("End-->/find/consumer-rewards/"+customerId);
		}
		catch(ResourceNotFoundException osExp) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Controller Warning: Sorry, Unable to fetch customer details!!!");
		}
		return new ResponseEntity<>(orderDetails, HttpStatus.NO_CONTENT);
	}
}
