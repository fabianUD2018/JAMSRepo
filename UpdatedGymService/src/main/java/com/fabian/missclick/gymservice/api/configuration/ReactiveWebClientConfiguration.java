package com.fabian.missclick.gymservice.api.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
//import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
//@LoadBalancerClient(name = "workout-service", configuration = WorkOutServiceCustomConfiguration.class)
public class ReactiveWebClientConfiguration {

	@Bean
	@LoadBalanced
	public WebClient.Builder webClientBuilder(){
		return  WebClient.builder();
	}
	
}
