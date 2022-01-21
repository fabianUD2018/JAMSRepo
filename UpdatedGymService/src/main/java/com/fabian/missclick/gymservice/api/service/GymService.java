package com.fabian.missclick.gymservice.api.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.fabian.missclick.gymservice.api.dto.ClientDTO;
import com.fabian.missclick.gymservice.api.dto.WorkoutDTO;
import com.fabian.missclick.gymservice.api.dto.WorkoutDTO.Builder;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class GymService {

	@Autowired
	private WebClient.Builder reactiveWebClient;

	@Autowired
	private ClientService clientService;

	@Autowired
	private ReactiveCircuitBreakerFactory reactiveCircuitBreakerFactory;

	public Flux<WorkoutDTO> getWorkOuts() {
		Flux<WorkoutDTO> response = reactiveWebClient.build().get().uri("http://WORKOUT-SERVICE/workOuts")
				.retrieve().bodyToFlux(WorkoutDTO.class)

				.transform(wOut -> {
					return reactiveCircuitBreakerFactory.create("workout-cb").run(wOut,
							throwable -> fallBackgetRoutineWithPreferedPeople(throwable));
				});

		return response;
	}

	public Mono<ClientDTO> getClientWithWorkOuts(String ClientName) {
		Mono<List<WorkoutDTO>> workOuts = getWorkOuts().collectList();
		Mono<ClientDTO> clientMono = clientService.getclientByName(ClientName);
		 

		return clientMono.zipWith(workOuts, (c, w) -> {
			c.setWorkouts(w);
			return c;
		});
		
	}

	private Flux<WorkoutDTO> fallBackgetRoutineWithPreferedPeople(Throwable throwable) {
		Logger.getAnonymousLogger().info(throwable.getMessage());
		return Flux.just(WorkoutDTO.builder().withDuration(0).withId("404_not_found").withName("Service not available")
				.withPort("fall back method").build());
	}

}
