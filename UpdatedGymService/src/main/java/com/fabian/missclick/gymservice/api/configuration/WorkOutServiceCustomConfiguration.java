package com.fabian.missclick.gymservice.api.configuration;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bouncycastle.crypto.prng.RandomGenerator;
import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.hypermedia.ServiceInstanceProvider;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import reactor.core.publisher.Flux;

@Configuration
public class WorkOutServiceCustomConfiguration {

	@Bean
	@Primary
	public ServiceInstanceListSupplier serviceInstanceListSupplier() {
		return new CustomServiceInstanceListProvider("workout-service");
	}
	
	class CustomServiceInstanceListProvider implements ServiceInstanceListSupplier {
		private final String serviceId;
		
		
		public CustomServiceInstanceListProvider(String serviceId) {
			super();
			this.serviceId = serviceId;
		}


		public String getServiceId() {
			return serviceId;
		}



		@Override
		public Flux<List<ServiceInstance>> get() {
			return Flux.just(
					Arrays.asList(
							new DefaultServiceInstance(serviceId+ "1", serviceId, "localhost", 8090, false), 
							new DefaultServiceInstance(serviceId+ "2", serviceId, "localhost", 8091, false),
							new DefaultServiceInstance(serviceId+ "3", serviceId, "localhost", 8092, false)
							)
					);
		}
		
	}
}
