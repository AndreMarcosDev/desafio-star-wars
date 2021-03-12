package com.desafio.api.model;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlanetInput {
	
	@ApiModelProperty(example = "Tatooine")
	@NotBlank
	private String name;
	
	@ApiModelProperty(example = "Arid")
	@NotBlank
	private String climate;
	
	@ApiModelProperty(example = "Dessert")
	@NotBlank
	private String terrain;

}
