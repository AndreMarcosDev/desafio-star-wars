package com.desafio.api.openapi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.desafio.api.exceptionhandler.Problem;
import com.desafio.api.model.PlanetInput;
import com.desafio.api.model.PlanetModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags="Planets")
public interface PlanetControllerOpenApi {
	
	@ApiOperation("Lista os planetas")
	Page<PlanetModel> listar(String name, Pageable pageable);
	
	@ApiOperation("Busca um planeta por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID do planeta inválido", response = Problem.class),
		@ApiResponse(code = 404, message = "Planeta não encontrado", response = Problem.class)
	})
	ResponseEntity<PlanetModel> buscar(
			@ApiParam(value = "ID do planeta", example = "1", required = true)
			Long id);
	
	@ApiOperation("Cadastra um planeta")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Planeta cadastrado"),
	})
	PlanetModel adicionar(
			@ApiParam(name = "corpo", value = "Representação de um novo planeta", required = true)
			PlanetInput planetInput) throws Exception;
	
	@ApiOperation("Exclui um planeta por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Planeta excluído"),
		@ApiResponse(code = 404, message = "Planeta não encontrado", response = Problem.class)
	})
	void remover(
			@ApiParam(value = "ID do planeta", example = "1", required = true)
			Long id);

}
