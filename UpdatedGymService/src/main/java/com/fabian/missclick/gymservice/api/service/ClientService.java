package com.fabian.missclick.gymservice.api.service;

import java.net.URI;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.fabian.missclick.gymservice.api.dto.ClientDTO;

import ch.qos.logback.core.net.server.Client;
import reactor.core.publisher.Mono;

@Service
public class ClientService {

	
	@Autowired
	private WebClient.Builder restTemplateClient;
	
	/*
	public ClientDTO getclientByName(String name) {
		HashMap<String, String> pathVariables = new HashMap<>();
		pathVariables.put("name", name);
		return restTemplate
				.getForObject("http://clients-service/clients/{name}", ClientDTO.class, pathVariables );
	}
	*/
	
	public Mono<ClientDTO> getclientByName(String name) {
		HashMap<String, String> pathVariables = new HashMap<>();
		pathVariables.put("name", name);
		
		return restTemplateClient
				.build()
				.get()
				.uri("http://people-service/clients/{name}",  pathVariables )
				.retrieve()
				.bodyToMono(ClientDTO.class);
	}
}
