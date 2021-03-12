package com.desafio.api.client;

import java.util.ArrayList;

import com.desafio.api.client.model.PlanetStarWars;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class RequestWrapper {
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	private ArrayList<PlanetStarWars> results;

	public ArrayList<PlanetStarWars> getResults() {
		return results;
	}

	public void setResults(ArrayList<PlanetStarWars> results) {
		this.results = results;
	}

}
