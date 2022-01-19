package com.fabian.missclick.gymservice.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.fabian.missclick.gymservice.api.dto.WorkoutDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class GymService {

	@Autowired
	private WebClient.Builder reactiveWebClient;
	
	public Mono<ServerResponse> getRoutineWithPreferedPeople(){
		Flux<WorkoutDTO> response = reactiveWebClient.build()
		.get()
		.uri("http://workout-service:8888/workOuts")
		.retrieve()
		.bodyToFlux(WorkoutDTO.class);
		
		return ServerResponse.ok().body(response, WorkoutDTO.class);
	}
	
}
