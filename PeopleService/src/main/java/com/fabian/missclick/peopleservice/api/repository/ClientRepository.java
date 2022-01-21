package com.fabian.missclick.peopleservice.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fabian.missclick.peopleservice.api.entities.Client;

public interface ClientRepository extends JpaRepository<Client, String>{

	public Optional<Client> findByName(String name);
	
	public List<Client> findAllByOrderByNameDesc();
	 
}
