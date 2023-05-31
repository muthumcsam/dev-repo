# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.0.6/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.0.6/maven-plugin/reference/html/#build-image)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.0.6/reference/htmlsingle/#data.sql.jpa-and-spring-data)
* [Spring Security](https://docs.spring.io/spring-boot/docs/3.0.6/reference/htmlsingle/#web.security)
* [Config Client Quick Start](https://docs.spring.io/spring-cloud-config/docs/current/reference/html/#_client_side_usage)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.0.6/reference/htmlsingle/#web)

### Guides
The following guides illustrate how to use some features concretely:

* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

# Retail Analytic APIs for Consumer Rewsard Program

-[Project Repo]: https://github.com/muthumcsam/dev-repo/


## Table of contents

- Requirements
- Installation
- Configuration
- Information for developers
- Maintainers


## Requirements

A retailer offers a rewards program to its customers, awarding points based on each recorded purchase. 
 
A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every dollar spent between $50 and $100 in each transaction.
(e.g., a $120 purchase = 2x$20 + 1x$50 = 90 points).
 
Given a record of every transaction during a three-month period, calculate the reward points earned for each customer per month and total.
 
•	Solve using Spring Boot
•	Create a RESTful endpoint
•	Make up a data set to best demonstrate your solution
•	Check solution into GitHub



## Configuration & Installation
 1. Clone the code from the github repo :  https://github.com/muthumcsam/dev-repo/ 
     CMD> git clone https://github.com/muthumcsam/dev-repo/ .
     - this downloads the complete code under the folder "retail-analytics"

## Configuration
 1. Install MySQL DB
 2. Create a DB DEVDB and ensure that runs on the port : 3306
    sql : CREATE DATABASE `devdb` 
    jdbc url : jdbc:mysql://localhost:3306/DEVDB
 3. No need to create the table as the code creates tables and inserts the records.
 4. Go to "/retail-analytics/src/main/resources/application.properties" file and set the below properties
       spring.datasource.password=<PROVIDE YOUR DB PASSWORD>
	   spring.jpa.hibernate.ddl-auto=update
	   spring.jpa.defer-datasource-initialization=true
  5. Note: spring.jpa.hibernate.ddl-auto=update & spring.jpa.defer-datasource-initialization=true are set to the above said values for the first time.
	   For the consequtive run these properties has to be reset to like below during the next application run. 
	   spring.jpa.hibernate.ddl-auto=none
	   spring.jpa.defer-datasource-initialization=false
  6. Go to "/retail-analytics"
  7. Open <CMD> prompt and run  >mvn clean install
  8. Upon successful compilation, Go to the foler .\retail-analytics\target and copy the "retail-analytics-1.0.0-SNAPSHOT.jar" to any location 
     where the java/jvm version "17.0.6" and above has been installed.
  9. Run "java -jar retail-analytics-1.0.0-SNAPSHOT.jar" 
 10. Access uri from the PostMan API : http://localhost:9001/retail/find-customer-orders/3 
 
	   

## API Information

1.Get All the transaction details for the consumer
  http://localhost:9001/retail/find-customer-orders/{customerId}
  This API takes customer_id as a input (Refer:Customer Table) and lists the consolidated order transaction details for the customer up to date.
  Sample Outout:
    [
		{
			"orderId": 6,
			"customerId": 3,
			"orderDate": "2023-05-03T09:19:58.000+00:00",
			"shippingDate": "2023-05-03T09:19:58.000+00:00",
			"quantity": 5,
			"products": {
				"productId": 1,
				"productName": "SHIRT",
				"category": "APPEARELS",
				"retailPrice": 50.0,
				"createdDate": "2023-05-03T09:19:58.000+00:00"
			},
			"customers": {
				"customerId": 3,
				"customerName": "KIM",
				"address": "CUMMING",
				"email": "kim@testmail.com",
				"phone": 6854563433
			},
			"productid": 1
       },
	   :
	   :
	]
2.Gets the Total spending and reward calculations for each customer for each and every month.
  http://localhost:9001/retail/find/montly-rewards
  Sample Output:
      {
		"1": [
				{
					"customerId": 1,
					"totalCost": 1100.0,
					"customerName": "MIC",
					"rewardPoints": 2050,
					"monthOfyear": "May"
				},
				{
					"customerId": 1,
					"totalCost": 3050.0,
					"customerName": "MIC",
					"rewardPoints": 5950,
					"monthOfyear": "March"
				},
				{
					"customerId": 1,
					"totalCost": 600.0,
					"customerName": "MIC",
					"rewardPoints": 1050,
					"monthOfyear": "April"
				}
		],
		"2": [
				{
					"customerId": 2,
					"totalCost": 3500.0,
					"customerName": "ROB",
					"rewardPoints": 6850,
					"monthOfyear": "May"
				},
				{
					"customerId": 2,
					"totalCost": 100.0,
					"customerName": "ROB",
					"rewardPoints": 50,
					"monthOfyear": "March"
				},
				{
					"customerId": 2,
					"totalCost": 1500.0,
					"customerName": "ROB",
					"rewardPoints": 2850,
					"monthOfyear": "April"
				}
		]
	}
    
3.Gets the Total spending and reward calculations for given customer.
  http://localhost:9001/retail/find/consumer-rewards/{customerId}
  Sample Output:
    {
    "customerId": 5,
    "totalCost": 5100.0,
    "customerName": "DAV",
    "rewardPoints": 10050,
    "monthOfyear": null
   }


### Table schemas:

--Customer

CREATE TABLE `customer` (
  `customer_id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `customer_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` bigint DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--Product
CREATE TABLE `product` (
  `product_id` bigint NOT NULL AUTO_INCREMENT,
  `category` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `retail_price` double DEFAULT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--customer_order_details
CREATE TABLE `customer_order_details` (
  `order_id` bigint NOT NULL AUTO_INCREMENT,
  `customer_id` bigint DEFAULT NULL,
  `order_date` datetime(6) DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `shipping_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `fk_customer_id` (`customer_id`),
  KEY `fk_product_id` (`product_id`),
  CONSTRAINT `fk_customer_id` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`),
  CONSTRAINT `fk_product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

### Table Date:
   Refer : /retail-analytics/src/main/resources/data.sql
## Maintainers

### Current maintainers

- [Muthusamy Ramasamy ](https://www.linkedin.com/in/muthusamyr/)

