package com.fabian.missclick.gymservice.api.dto;

import java.util.List;

public class WorkoutDTO {

	private String id;

	private String port;

	private String name;

	private List<ExcerciseDTO> exercices;
	private int duration;

	public WorkoutDTO(Builder builder) {
		
		this.setId(builder.id);
		this.setName(builder.name);
		this.setPort(builder.port);
		this.setDuration(builder.duration);}

	public WorkoutDTO() {
		// TODO Auto-generated constructor stub
	}

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

	public static Builder builder() {
		return new Builder();
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

	public static class Builder
	{
		private String id;

		private String port;

		private String name;
		
		private int duration;
		
		
		
		public Builder() {
			super();
		}

		public  Builder withId(String newID ) {
			id = newID;
			return this;
		}
		
		public  Builder withPort(String port ) {
			this.port = port;
			return this;
		}
		
		
		public  Builder withName(String name ) {
			this.name = name;
			return this;
		}
		
		public Builder withDuration(int duration) {
			this.duration= duration;
			return this;
		}
		
		public WorkoutDTO buildWithOutParent() {
			WorkoutDTO dto = new WorkoutDTO();
			dto.setId(id);
			dto.setName(name);
			dto.setPort(port);
			return dto;
		}
		public WorkoutDTO build() {
			
			return new WorkoutDTO(this);
		}
	}

}
