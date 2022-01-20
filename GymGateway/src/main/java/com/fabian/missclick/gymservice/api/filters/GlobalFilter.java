package com.fabian.missclick.gymservice.api.filters;

import java.util.List;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.context.ApplicationContext;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class GlobalFilter implements org.springframework.cloud.gateway.filter.GlobalFilter {

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		ServerHttpRequest request = exchange.getRequest();
		request.mutate().headers(h->h.add("userName", "Mr. gateway pre filter header "));
		return chain.filter(exchange)
				.then(Mono.fromRunnable(()->{
					String userName = exchange.getRequest().getHeaders().getFirst("userName");
					exchange.getResponse().getHeaders().add("responseFromGW", "Nice to meet you  " + 
										userName + "I am PostRequest Filter");
				}));
	}

}
