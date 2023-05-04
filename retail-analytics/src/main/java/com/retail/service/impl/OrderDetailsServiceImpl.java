package com.retail.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.retail.exception.OrderDetailsRepoDaoException;
import com.retail.exception.ResourceNotFoundException;
import com.retail.exception.RewardCalculationException;
import com.retail.model.CustomerOrderDetails;
import com.retail.model.CustomerSpendingAndRewardGroup;
import com.retail.repo.OrderDetailsRepository;
import com.retail.service.OrderDetailsService;
import com.retail.util.RewardConfigUtils;

/**
 * Service class for ReatilAnalyticsController
 * 
 * @author muthu
 *
 */
@Service
@Component("OrderDetailsServiceImpl")
public class OrderDetailsServiceImpl implements OrderDetailsService {
	@Autowired
	private final OrderDetailsRepository orderdetailsRepo;
	@Autowired
	private RewardConfigUtils configUtils;
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderDetailsServiceImpl.class);

	public OrderDetailsServiceImpl(OrderDetailsRepository orderdetailsRepo, RewardConfigUtils configUtils) {
		this.orderdetailsRepo = orderdetailsRepo;
		this.configUtils = configUtils;
	}

	/**
	 * Method to find the list of provided Customer's Order Details.
	 * 
	 * @return List<CustomerOrderDetails>
	 */
	public List<CustomerOrderDetails> findByCustomerId(Long customerId) throws ResourceNotFoundException {
		LOGGER.info("-->OrderDetailsServiceImpl.findByCustomerId");
		List<CustomerOrderDetails> resource = null;
		try {
			resource = orderdetailsRepo.findByCustomerId(customerId);
		} catch (OrderDetailsRepoDaoException daoExp) {
			throw new ResourceNotFoundException(daoExp);
		}
		return resource;
	}

	/**
	 * Method to find the list of provided Customer's Order Details total purchase
	 * cost along with Rewards obtained.
	 * 
	 * @return Map<Long, List<CustomerSpendingAndRewardGroup>>
	 */
	public Map<Long, List<CustomerSpendingAndRewardGroup>> getCustomerOrderDetailsGroupByCustomerId()
			throws ResourceNotFoundException {
		LOGGER.info("-->OrderDetailsServiceImpl.getCustomerOrderDetailsGroupByCustomerId");
		List<CustomerSpendingAndRewardGroup> customerSpending = null;
		Map<Long, List<CustomerSpendingAndRewardGroup>> namesPerId = null;
		try {
			customerSpending = orderdetailsRepo.getCustomerOrderDetailsGroupByCustomerId();
			namesPerId = customerSpending.stream().map(reward -> {
				try {
					return calculateRewards(reward);
				} catch (RewardCalculationException e) {
					LOGGER.info("Error while calculating reward for " + reward.getCustomerName(), e.getMessage());
				}
				return reward;
			}).collect(Collectors.groupingBy(grooupByIdKey -> grooupByIdKey.getCustomerId(), Collectors.toList()));

		} catch (OrderDetailsRepoDaoException daoExp) {
			throw new ResourceNotFoundException(daoExp);
		}
		return namesPerId;
	}

	/**
	 * Method to find the list of provided Customer's Order Details total purchase
	 * cost along with Rewards obtained.
	 * 
	 * @return Map<Long, List<CustomerSpendingAndRewardGroup>>
	 */
	public CustomerSpendingAndRewardGroup getCustomerOrderDetailsByCustomerId(Long customerId)
			throws ResourceNotFoundException {
		LOGGER.info("-->OrderDetailsServiceImpl.getCustomerOrderDetailsByCustomerId");
		CustomerSpendingAndRewardGroup customerSpending = null;
		try {
			customerSpending = orderdetailsRepo.getCustomerOrderDetailsByCustomerId(customerId);
			if(customerSpending != null) {
				try {
					return calculateRewards(customerSpending);
				} catch (RewardCalculationException e) {
					LOGGER.info("Error while calculating reward for " +customerSpending.getCustomerName(), e.getMessage());
				}
			}

		} catch (OrderDetailsRepoDaoException daoExp) {
			throw new ResourceNotFoundException(daoExp);
		}
		return customerSpending;
	}
	
	/**
	 * Utility method for calculating the rewards for the provided customer spending
	 * 
	 * @param customerSpendingAndRewardGroup
	 * @return customerSpendingAndRewardGroup
	 */
	public CustomerSpendingAndRewardGroup calculateRewards(
			CustomerSpendingAndRewardGroup customerSpendingAndRewardGroup) throws RewardCalculationException {
		try {
			LOGGER.info("-->OrderDetailsServiceImpl.getCustomerOrderDetailsGroupByCustomerId");
			int silverBonusPoints = configUtils.getSilverRewardBonusPoints();
			int goldBonusPoints = configUtils.getGoldRewardBonusPoints();
			double totalCost = customerSpendingAndRewardGroup.getTotalCost();
			if (totalCost > silverBonusPoints && totalCost <= goldBonusPoints) {
				customerSpendingAndRewardGroup.setRewardPoints(Math.round(totalCost - silverBonusPoints));
			} else if (totalCost > goldBonusPoints) {
				Long goldReward = (Math.round(totalCost - goldBonusPoints) * 2) + goldBonusPoints - silverBonusPoints;
				customerSpendingAndRewardGroup.setRewardPoints(goldReward);
			} else
				customerSpendingAndRewardGroup.setRewardPoints(0L);
		} catch (Exception rewardExp) {
			throw new RewardCalculationException("Error while calculting rewards", rewardExp);
		}
		return customerSpendingAndRewardGroup;
	}
}
