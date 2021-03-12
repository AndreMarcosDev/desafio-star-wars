package com.desafio.domain.exception;

public class PlanetNotFoundException extends EntidadeNaoEncontradaException{
	
	private static final long serialVersionUID = 1L;
	
	public PlanetNotFoundException(Long id) {
		super(String.format("Não existe um cadastro de planeta com código %d", id));
	}
	
	public PlanetNotFoundException(String name) {
		super(String.format("Não existe um cadastro de planeta com nome %d", name));
	}

}
