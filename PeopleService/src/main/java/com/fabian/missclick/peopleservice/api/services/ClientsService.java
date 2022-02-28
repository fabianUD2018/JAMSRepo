package com.fabian.missclick.peopleservice.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fabian.missclick.peopleservice.api.entities.Client;
import com.fabian.missclick.peopleservice.api.repository.ClientRepository;

@Service
public class ClientsService {

	@Autowired
	private ClientRepository repository;
	
	public Client findClientByName(String name) {
		
		if (name.endsWith("error")) {
			throw new RuntimeException("error to test circuitbreaker");
		}
		return repository.findByName(name).orElseThrow(()-> new RuntimeException("Requested Client does not Exist"));
	}

	public Client saveNewClient(Client client) {
		
		return repository.save(client);
	}

	public List<Client> findAllClientsOrderedByName() {
		// TODO Auto-generated method stub
		return repository.findAllByOrderByNameDesc();
	}
	
}
