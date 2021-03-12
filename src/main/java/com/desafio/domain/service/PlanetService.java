package com.desafio.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.desafio.api.client.PlanetClient;
import com.desafio.api.client.RequestWrapper;
import com.desafio.api.client.exception.PlanetBadRequestException;
import com.desafio.domain.exception.NegocioException;
import com.desafio.domain.exception.PlanetNotFoundException;
import com.desafio.domain.model.Planet;
import com.desafio.domain.repository.PlanetRepository;

@Service
public class PlanetService {
	
	@Autowired
	private PlanetRepository planetRepository;
	
	@Autowired
	private PlanetClient planetClient;
	
	@Transactional
	public Planet salvar(Planet planet) {
		planetRepository.detach(planet);
		
		Optional<Planet> usuarioExistente = planetRepository.findByName(planet.getName());
		
		if(usuarioExistente.isPresent()) {
			throw new NegocioException( String.format("Já existe um planeta cadastrado com o nome %s", planet.getName()));
		}
		
		return planetRepository.save(planet);
	}
	
	@Transactional
	public void excluir(Long id) {
		
		try{
			
			planetRepository.deleteById(id);
			planetRepository.flush();
		
		} catch(EmptyResultDataAccessException e) {
			throw new PlanetNotFoundException(id);
		}
		
	}
	
	public int getNumberOfFilms(String name) throws Exception {

		int films;

		ResponseEntity<RequestWrapper> wrapper = planetClient.getPlanetByName(name);

		if (wrapper.getBody().getResults().size() == 0) {

			throw new PlanetBadRequestException(String.format("O planeta de nome %s não existe no mundo de StarWars!", name));
			
		} else {
			
			films = wrapper.getBody().getResults().get(0).getFilms().size();
		}

		return films;

	}
	
	public Planet findOrFail(Long Id) {
		return planetRepository.findById(Id)
				               .orElseThrow( () -> 
				                   new PlanetNotFoundException(Id)
				               );
	}
	
	public Planet findByNameOrFail(String name) {
		return planetRepository.findByName(name)
				               .orElseThrow( () -> 
				                   new PlanetNotFoundException(name)
				               );
	}

}
