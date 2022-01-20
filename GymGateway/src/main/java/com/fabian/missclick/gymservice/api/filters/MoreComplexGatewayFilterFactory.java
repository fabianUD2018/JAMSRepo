package com.fabian.missclick.gymservice.api.filters;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@Component
public class MoreComplexGatewayFilterFactory extends AbstractGatewayFilterFactory<MoreComplexGatewayFilterFactory.Configuration>{

	
	
	public MoreComplexGatewayFilterFactory() {
		super(Configuration.class);
		// TODO Auto-generated constructor stub
	}


	public static class Configuration {
		
		private String complexMessage;
		private int times;
		public String getComplexMessage() {
			return complexMessage;
		}
		public void setComplexMessage(String complexMessage) {
			this.complexMessage = complexMessage;
		}
		public int getTimes() {
			return times;
		}
		public void setTimes(int times) {
			this.times = times;
		}

	}

	@Override
	public GatewayFilter apply(Configuration config) {
		// TODO Auto-generated method stub
		
		return (exchange, chain)->{
			
			return chain.filter(exchange).then(Mono.fromRunnable(()->{
					exchange.getResponse().getHeaders().add("moreComplexMessage", "repeat "+ config.getComplexMessage() + config.getTimes() + "times");
			}));
					
		};
	}

}
