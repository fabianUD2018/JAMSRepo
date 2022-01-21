package com.fabian.missclick.peopleservice.api.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fabian.missclick.peopleservice.api.entities.Client;
import com.fabian.missclick.peopleservice.api.services.ClientsService;

@RestController()
@RequestMapping(path = "clients")
//@CrossOrigin(methods = RequestMethod.POST)
public class ClientController {
	@Autowired
	private ClientsService clientsService;

	@GetMapping("/{name}")
	public ResponseEntity<Client> getClientById(@PathVariable("name") String name) {

		return ResponseEntity.ok(clientsService.findClientByName(name));

	}

	@PostMapping()
	public ResponseEntity<Client> postClient(@RequestBody Client client) {
		return ResponseEntity.created(URI.create("client/" + client.getName()))
				.body(clientsService.saveNewClient(client));

	}
	@GetMapping()
	public ResponseEntity<List<Client>> getClients() {

		return ResponseEntity.ok(clientsService.findAllClientsOrderedByName());

	}
}
