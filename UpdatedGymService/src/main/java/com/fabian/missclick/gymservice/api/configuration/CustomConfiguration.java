package com.fabian.missclick.gymservice.api.configuration;

import java.time.Duration;

import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;

@Configuration
public class CustomConfiguration {
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		
		return new RestTemplate();
	}
	
	@Bean
	public  Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer(){
		
		return factory->factory.configureDefault(id->{
			return new Resilience4JConfigBuilder(id)
					.circuitBreakerConfig(
							CircuitBreakerConfig.custom()
								.slidingWindowSize(10)
								.failureRateThreshold(50)
								.waitDurationInOpenState(Duration.ofSeconds(10L))
								.permittedNumberOfCallsInHalfOpenState(10)
								.slowCallRateThreshold(50)
								.slowCallDurationThreshold(Duration.ofSeconds(3L))
								.build())
					.timeLimiterConfig(TimeLimiterConfig.custom()
										.timeoutDuration(Duration.ofSeconds(5L))
										.build())
					.build();
		});
	}

}
