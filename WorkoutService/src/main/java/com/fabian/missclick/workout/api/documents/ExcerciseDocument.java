package com.fabian.missclick.workout.api.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
@Document(collection = "exercise")
public class ExcerciseDocument {
	@Id
	private String id ;
	
	private String name;
	@Field(name="muscular_approach")
	private String muscularApproach;
	private int sets;
	private int repetitions;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMuscularApproach() {
		return muscularApproach;
	}
	public void setMuscularApproach(String muscularApproach) {
		this.muscularApproach = muscularApproach;
	}
	public int getSets() {
		return sets;
	}
	public void setSets(int sets) {
		this.sets = sets;
	}
	public int getRepetitions() {
		return repetitions;
	}
	public void setRepetitions(int repetitions) {
		this.repetitions = repetitions;
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
		builder.append("ExcerciseDocument [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", muscularApproach=");
		builder.append(muscularApproach);
		builder.append(", sets=");
		builder.append(sets);
		builder.append(", repetitions=");
		builder.append(repetitions);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
}
