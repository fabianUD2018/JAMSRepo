package com.fabian.missclick.gymservice.api.dto;

import java.util.List;



public class WorkoutDTO {
	
	private String id;
	
	private String port;
	
	private String name;
	
	private List<ExcerciseDTO> exercices;
	private int duration;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public List<ExcerciseDTO> getExercices() {
		return exercices;
	}
	public void setExercices(List<ExcerciseDTO> exercices) {
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
	
	
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
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
