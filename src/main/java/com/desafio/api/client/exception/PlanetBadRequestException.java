package com.desafio.api.client.exception;

public class PlanetBadRequestException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PlanetBadRequestException(String exception) {
		super(exception);
	}

}
