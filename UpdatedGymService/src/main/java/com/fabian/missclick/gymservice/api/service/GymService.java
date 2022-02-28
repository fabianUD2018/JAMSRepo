package com.fabian.missclick.gymservice.api.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.fabian.missclick.gymservice.api.dto.ClientDTO;
import com.fabian.missclick.gymservice.api.dto.WorkoutDTO;
import com.fabian.missclick.gymservice.api.dto.WorkoutDTO.Builder;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
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
		Flux<WorkoutDTO> response = reactiveWebClient.build().get().uri("http://WORKOUT-SERVICE/workOuts").retrieve()
				.bodyToFlux(WorkoutDTO.class)

				.transform(wOut -> {
					return reactiveCircuitBreakerFactory.create("workout-cb").run(wOut,
							throwable -> fallBackgetRoutineWithPreferedPeople(throwable));
				});

		return response;
	}

	public Mono<ClientDTO> getClientWithWorkOuts(String clientName) {
		if (clientName.endsWith("error")) {
			throw new RuntimeException("test on gym service");
		}
		Mono<List<WorkoutDTO>> workOuts = getWorkOuts().collectList();
		Mono<ClientDTO> clientMono = clientService.getclientByName(clientName);
		ClientDTO dto = new ClientDTO();
		dto.setAge(0);
		dto.setBodyCondition(0);
		dto.setName("Mr. Nobody 23");

		return clientMono.defaultIfEmpty(dto).zipWith(workOuts, (c, w) -> {
			c.setWorkouts(w);
			return c;
		});

	}

	public Mono<ClientDTO> circuitBreakerGetClientWithWorkOuts(String clientName) {
		return reactiveCircuitBreakerFactory.create("workout-cb2").run(getClientWithWorkOuts(clientName),
				t -> fallBackClientWithWorkOuts(t));
	}
	
	@CircuitBreaker(name ="default", fallbackMethod = "fallBackClientWithWorkOuts ")
	public Mono<ClientDTO> circuitBreakerAnnotationGetClientWithWorkOuts(String clientName) {
		return getClientWithWorkOuts(clientName);
	}

	private Mono<ClientDTO> fallBackClientWithWorkOuts(Throwable t) {
		ClientDTO dto = new ClientDTO();
		dto.setAge(0);
		dto.setBodyCondition(0);
		dto.setName("Mr. Nobody");
		return Mono.just(dto);
	}

	private Flux<WorkoutDTO> fallBackgetRoutineWithPreferedPeople(Throwable throwable) {
		Logger.getAnonymousLogger().info(throwable.getMessage());
		return Flux.just(WorkoutDTO.builder().withDuration(0).withId("404_not_found").withName("Service not available")
				.withPort("fall back method").build());
	}

	public Mono<WorkoutDTO> postWorkOut(ServerRequest request) {
		Mono<WorkoutDTO> dto = request.bodyToMono(WorkoutDTO.class);
	
		return dto.flatMap( object->{
			return 	reactiveWebClient
						.build()
						.post()
						.uri("http://workout-service/workOuts")
						.body(Mono.just(object), WorkoutDTO.class)
						.retrieve()
						.bodyToMono(WorkoutDTO.class)
						;
					
					}).transform(result->{
						return 	 reactiveCircuitBreakerFactory
								.create("workout-cb3")
								.run(result, error->fallBackErrorCreatingWorkout(error));
					});

						
	}

	private Mono<WorkoutDTO> fallBackErrorCreatingWorkout(Throwable error) {
		return Mono.just(WorkoutDTO.builder().withName("Default workout becouse CBError: "+  error.getMessage()).build());
		
	}

}
