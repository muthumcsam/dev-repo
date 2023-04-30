package com.retail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RetailAnalyticsApplication {

	public static void main(String[] args) {
		SpringApplication.run(RetailAnalyticsApplication.class, args);
	}
	
	public void run() {
		System.out.println("Test");
	}

}
