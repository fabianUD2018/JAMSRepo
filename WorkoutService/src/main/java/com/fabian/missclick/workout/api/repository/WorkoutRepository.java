package com.fabian.missclick.workout.api.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.fabian.missclick.workout.api.documents.WorkoutDocument;

@Repository
public interface WorkoutRepository extends ReactiveMongoRepository<WorkoutDocument, String> {

}
