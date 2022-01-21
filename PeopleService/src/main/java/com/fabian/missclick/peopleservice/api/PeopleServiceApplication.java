package com.fabian.missclick.peopleservice.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
@EnableEurekaClient
@SpringBootApplication
public class PeopleServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PeopleServiceApplication.class, args);
	}

}
