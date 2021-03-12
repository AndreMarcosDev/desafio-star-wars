package com.desafio.api.client.model;

import java.util.ArrayList;

import lombok.Data;

@Data
public class PlanetStarWars {
	
	private String name;
    private String diameter;
    private String gravity;
    private String population;
    private String climate;
    private String terrain;
    private String created;
    private String edited;
    private String url;
    private String rotationPeriod;
    private String orbitalPeriod;
    private String surfaceWater;
    private ArrayList<String> residents;
    private ArrayList<String> films;

}
