package com.desafio.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.desafio.api.model.PlanetModel;
import com.desafio.domain.model.Planet;

@Component
public class PlanetModelAssembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public PlanetModel toModel(Planet planet) {
		return modelMapper.map(planet, PlanetModel.class);
	}
	
	public List<PlanetModel> toCollectionModel(List<Planet> planets) {
		return planets.stream()
			       .map(planet -> toModel(planet))
				   .collect(Collectors.toList());
	}

}
