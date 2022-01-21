package com.fabian.missclick.gymservice.api.dto;

import java.util.List;

public class ClientDTO {
	
	private String name;
	
	private int age;
	
	private int bodyCondition;
	
	private String sex;
	
	private int trainingExperience;
	
	
	private List<WorkoutDTO> workouts;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getBodyCondition() {
		return bodyCondition;
	}

	public void setBodyCondition(int bodyCondition) {
		this.bodyCondition = bodyCondition;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getTrainingExperience() {
		return trainingExperience;
	}

	public void setTrainingExperience(int trainingExperience) {
		this.trainingExperience = trainingExperience;
	}

	public List<WorkoutDTO> getWorkouts() {
		return workouts;
	}

	public void setWorkouts(List<WorkoutDTO> workouts) {
		this.workouts = workouts;
	}
	
	
	
}
