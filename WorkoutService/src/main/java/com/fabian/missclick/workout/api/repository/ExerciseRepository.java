package com.fabian.missclick.workout.api.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.fabian.missclick.workout.api.documents.ExcerciseDocument;

@Repository
public interface ExerciseRepository extends ReactiveMongoRepository<ExcerciseDocument, String>{

}
