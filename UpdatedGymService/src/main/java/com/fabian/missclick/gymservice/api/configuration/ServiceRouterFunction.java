package com.fabian.missclick.gymservice.api.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.fabian.missclick.gymservice.api.service.GymHandler;
import com.fabian.missclick.gymservice.api.service.GymService;

@Configuration
public class ServiceRouterFunction {

	@Autowired
	private GymHandler handler;
	
	@Bean
	public RouterFunction<ServerResponse> controller(){
		return RouterFunctions
				.route(RequestPredicates.GET("gymservice/workouts"), req->handler.getWorkOuts())
				.andRoute(RequestPredicates.POST("gymservice/workouts"), req->handler.postWorkOut(req))
				.andRoute(RequestPredicates.GET("gymservice/clientWorkout/{name}"),  req->handler.getClientAndWorkOuts(req));
	}
}
