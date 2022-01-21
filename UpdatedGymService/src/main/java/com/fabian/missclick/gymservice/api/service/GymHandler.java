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
		routine.doOnNext(System.out::println);
		return routine
		.collectList()
		.flatMap(r -> {
			if (r.size()==1 && r.get(0).getDuration()==0){
				return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Mono.just(r), 				WorkoutDTO.class);
			}
			return ServerResponse.ok().body(Mono.just(r), WorkoutDTO.class);
		});
	}
	
	public Mono<ServerResponse> getClientAndWorkOuts(ServerRequest request){
		String name = request.pathVariable("name");
		return ServerResponse.ok().body(gymService.getClientWithWorkOuts(name), ClientDTO.class);
		
	}
	
}
