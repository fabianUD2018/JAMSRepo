package com.fabian.missclick.gymservice.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.fabian.missclick.gymservice.api.dto.ClientDTO;
import com.fabian.missclick.gymservice.api.dto.WorkoutDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class GymHandler {

	@Autowired
	private GymService gymService;
	
	
	public Mono<ServerResponse> getWorkOuts(){
		Flux<WorkoutDTO> routine = gymService.getWorkOuts();
		routine.log();
		return routine
		.collectList()
		.flatMap(r -> {
			return ServerResponse.ok().body(Mono.just(r), WorkoutDTO.class);
		});
	}
	
	public Mono<ServerResponse> getClientAndWorkOuts(ServerRequest request){
		String name = request.pathVariable("name");
		return ServerResponse.ok().body(gymService.circuitBreakerGetClientWithWorkOuts(name), ClientDTO.class);
		
	}
	public Mono<ServerResponse> postWorkOut(ServerRequest request){
		
		return ServerResponse.ok().body(gymService.postWorkOut(request ), WorkoutDTO.class);
		
	}
	
}
