package com.desafio.api.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.api.ResourceUriHelper;
import com.desafio.api.assembler.PlanetInputDisassembler;
import com.desafio.api.assembler.PlanetModelAssembler;
import com.desafio.api.model.PlanetInput;
import com.desafio.api.model.PlanetModel;
import com.desafio.api.openapi.controller.PlanetControllerOpenApi;
import com.desafio.domain.model.Planet;
import com.desafio.domain.repository.PlanetRepository;
import com.desafio.domain.service.PlanetService;

@RestController
@RequestMapping(value = "/planets")
public class PlanetController implements PlanetControllerOpenApi {
	
	@Autowired
	private PlanetRepository planetRepository;
	
	@Autowired
	private PlanetService planetService;
	
	@Autowired
	private PlanetModelAssembler planetModelAssembler;
	
	@Autowired
	private PlanetInputDisassembler planetInputDisassembler;
	
	@Override
	@GetMapping()
	public Page<PlanetModel> listar(String name, 
			                        @PageableDefault(size = 10) Pageable pageable) {
		
		Page<Planet> planetsPage = null;
		
		if(name != null) {
			planetsPage = planetRepository.findByName(pageable, name);
		} else {
			planetsPage = planetRepository.findAll(pageable);
		}
		
		
		List<PlanetModel> planetsModel = planetModelAssembler.toCollectionModel(planetsPage.getContent());
		
		Page<PlanetModel> planetsModelPage = new PageImpl<>(planetsModel, pageable, planetsPage.getTotalElements());
		
		return planetsModelPage;
	}
	
	@Override
	@GetMapping("/{id}")
	public ResponseEntity<PlanetModel> buscar(@PathVariable Long id) {
		Planet planet = planetService.findOrFail(id);
		
		return ResponseEntity.ok()
			       .cacheControl(CacheControl.maxAge(30, TimeUnit.SECONDS))
				   .body(planetModelAssembler.toModel(planet));
	}
	
	@Override
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public PlanetModel adicionar(@RequestBody @Valid PlanetInput planetInput) throws Exception {
		
		Planet planet = planetInputDisassembler.toDomainObject(planetInput);
		
		planet.setFilms(planetService.getNumberOfFilms(planet.getName()));
		
		planet = planetService.salvar(planet);
		
		PlanetModel planetModel = planetModelAssembler.toModel(planet);
		
		ResourceUriHelper.addUriInResponseHeader(planetModel.getId());
		
		return planetModel;
	}
	
	@Override
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		planetService.excluir(id);
	}

}
