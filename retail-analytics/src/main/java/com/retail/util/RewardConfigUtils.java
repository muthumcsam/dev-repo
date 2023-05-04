package com.retail.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:reward-config.properties")
public class RewardConfigUtils {

  @Value("${retail.customer.reward.silver}")
  private Integer silverRewardBonusPoints;

  @Value("${retail.customer.reward.gold}")
  private Integer goldRewardBonusPoints;
  
  public RewardConfigUtils() {
	  
  }
  public Integer getSilverRewardBonusPoints() {
      return silverRewardBonusPoints;
  }

  public Integer getGoldRewardBonusPoints() {
      return goldRewardBonusPoints;
  }

}