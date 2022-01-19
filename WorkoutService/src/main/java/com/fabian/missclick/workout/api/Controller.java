package com.fabian.missclick.workout.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Configuration
public class Controller {

	@Autowired
	private WorkOutService service;

	@Bean
	public RouterFunction<ServerResponse> composedRoutes() {

		return RouterFunctions.route(RequestPredicates.GET("/workOuts"),
				req -> service.findAllDocuments())
				.andRoute(RequestPredicates.GET("/exercises"), req-> service.getAllExercises())
				.andRoute(RequestPredicates.POST("/workOuts"), req -> service.postWortkout(req));
	}

}
