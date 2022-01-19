package com.fabian.missclick.gymservice.api.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.fabian.missclick.gymservice.api.service.GymService;

@Configuration
public class ServiceRouterFunction {

	@Autowired
	private GymService service;
	
	@Bean
	public RouterFunction<ServerResponse> controller(){
		return RouterFunctions.route(RequestPredicates.GET("gymservice"), req->service.getRoutineWithPreferedPeople())
				.andRoute(RequestPredicates.GET("test"),  req->service.getRoutineWithPreferedPeople());
	}
}
