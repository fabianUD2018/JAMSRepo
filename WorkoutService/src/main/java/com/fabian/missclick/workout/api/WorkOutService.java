package com.fabian.missclick.workout.api;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.fabian.missclick.workout.api.documents.ExcerciseDocument;
import com.fabian.missclick.workout.api.documents.WorkoutDocument;
import com.fabian.missclick.workout.api.repository.ExerciseRepository;
import com.fabian.missclick.workout.api.repository.WorkoutRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class WorkOutService {

	@Autowired
	private WorkoutRepository repository;

	@Autowired
	private ExerciseRepository repositoryExcercise;
	
	@Autowired
	Environment environment;


	public Mono<ServerResponse> findAllDocuments() {
		String port = environment.getProperty("local.server.port");

		return repository.findAll()
				.map(currrentWO->{
					currrentWO.port= port;
					return currrentWO;
					
				})
				.collectList().flatMap(wo -> {
			if (!wo.isEmpty()) {
				
				return ServerResponse.ok().body(Mono.just(wo), WorkoutDocument.class);
			}
			return Mono.empty();
		}).switchIfEmpty(ServerResponse.noContent().build());
	}

	public Mono<ServerResponse> postWortkout(ServerRequest req) {
		Mono<WorkoutDocument> wD = req.bodyToMono(WorkoutDocument.class);
		
		return wD
				
				/*
				 * .flatMap(w -> { Flux<ExcerciseDocument> savedExcercises =
				 * repositoryExcercise.saveAll(w.getExercices()); return
				 * savedExcercises.collectList().map(list -> { System.err.println(list);
				 * w.setExercices(list); return w; }); })
				 */
				.flatMap(updatedWO -> {
					if (updatedWO.getName().endsWith("error")) {
						throw new RuntimeException("error to test circuitbreaker");
					}
					return repository.save(updatedWO);
					})	
				
		.flatMap(sW -> ServerResponse.created(URI.create("/workOuts/"+sW.getId())).body(Mono.just(sW), WorkoutDocument.class));
		/*.onErrorResume(error->{
			return ServerResponse.badRequest().body(Mono.just("the error es manage on on resume:"+error.getMessage()), String.class);});
		/*
		 * 	return wD.flatMap(w -> {
			Flux<ExcerciseDocument> savedExcercises = repositoryExcercise.saveAll(w.getExercices());
			return savedExcercises.collectList().map(list -> {
				w.setExercices(list);
				return w;
			}).doOnNext(System.out::println);
		}).flatMap(updatedWO -> repository.save(updatedWO)).flatMap(sW -> ServerResponse
				.created(URI.create("/workOuts/" + sW.getId())).body(Mono.just(sW), WorkoutDocument.class));
				*/
	}

	public Mono<ServerResponse> getAllExercises() {
		return ServerResponse.ok().body(repositoryExcercise.findAll(), ExcerciseDocument.class);
	}

}
