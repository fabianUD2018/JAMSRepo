package com.fabian.missclick.workout.api.documents;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class WorkoutDocument {

	@Id
	private String id;

	private String name;
	private List<ExcerciseDocument> exercices;
	private int duration;
	
	@Transient
	public String port;

	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ExcerciseDocument> getExercices() {
		return exercices;
	}

	public void setExercices(List<ExcerciseDocument> exercices) {
		this.exercices = exercices;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WorkoutDocument [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", exercices=");
		builder.append(exercices);
		builder.append(", duration=");
		builder.append(duration);
		builder.append("]");
		return builder.toString();
	}

}
