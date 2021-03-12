package com.desafio.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PlanetModel {
	
	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "Tatooine")
	private String name;
	
	@ApiModelProperty(example = "Arid")
	private String climate;
	
	@ApiModelProperty(example = "Desert")
	private String terrain;
	
	@ApiModelProperty(example = "1")
	private int films;

}
