package com.desafio;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.desafio.api.controller.PlanetController;
import com.desafio.domain.model.Planet;
import com.desafio.domain.repository.PlanetRepository;
import com.google.gson.Gson;

public class PlanetControllerTest extends DesafioStarWarsApplicationTests {

	private MockMvc mockMvc;

	@Autowired
	private PlanetController planetController;
	
	@Autowired
	private PlanetRepository repository;

	@Before
	public void begin(){
		// INJETA O CONTROLLER PARA QUE FUNCIONE COMO SE TIVESSE EXECUTANDO A APLICAÇÃO
		this.mockMvc = MockMvcBuilders.standaloneSetup(planetController).build();
		
		Planet planet = new Planet();
		planet.setName("Alderaan");
		planet.setClimate("temperate");
		planet.setTerrain("swamp, jungles");
		
		repository.save(planet);
	}
	
	@After
	public void end() {
		repository.deleteAll();
	}
	
	
	@Test
	public void canCreatePlanet() throws Exception {
		
		Planet planet = new Planet();
		planet.setName("Yavin IV");
		planet.setClimate("temperate");
		planet.setTerrain("swamp, jungles");

		this.mockMvc
				.perform(post("/planets")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(planet)))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(header().exists("Location"));
	}
	
	@Test
	public void cannotCreatePlanetIfIsNull() throws Exception {
		
		Planet planet = new Planet();
		planet.setName("");
		planet.setClimate("");
		planet.setTerrain("swamp, jungles");

		this.mockMvc
				.perform(post("/planets")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(planet)))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	@Test
	public void getIdSucess() throws Exception {
		
		Optional<Planet> planet = repository.findByName("Alderaan");
		
		this.mockMvc
				.perform(MockMvcRequestBuilders.get("/planets/" + planet.get().getId())
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("name").value("Alderaan"))
				.andExpect(jsonPath("climate").value("temperate"))
				.andExpect(jsonPath("terrain").value("swamp, jungles"))
				.andExpect(jsonPath("films").value(0));
	}

	@Test
	public void removeSucess() throws Exception {

		Optional<Planet> planet = repository.findByName("Alderaan");

		this.mockMvc
			.perform(MockMvcRequestBuilders.delete("/planets/" + planet.get().getId())
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isNoContent());
	}
	
	@Test
	public void nonExistsTest() throws Exception {

		this.mockMvc
			.perform(MockMvcRequestBuilders.get("/planets" + System.currentTimeMillis())
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isNotFound());

	}

}
