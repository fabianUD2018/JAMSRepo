package com.fabian.missclick.peopleservice.api.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "clients")
public class Client {
	
	@Id
	private String name;
	
	private int age;
	
	@Column(name = "body_condition")
	private int bodyCondition;
	
	private String sex;
	
	@Column(name = "training_experience")
	private int trainingExperience;
	
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
	
	
}
