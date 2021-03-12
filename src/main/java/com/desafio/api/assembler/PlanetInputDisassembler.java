package com.desafio.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.desafio.api.model.PlanetInput;
import com.desafio.domain.model.Planet;

@Component
public class PlanetInputDisassembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Planet toDomainObject(PlanetInput planetInput) {
		return modelMapper.map(planetInput, Planet.class);
	}
	
	public void copyToDomainObject(PlanetInput planetInput, Planet planet) {
		modelMapper.map(planetInput, planet);
	}

}
