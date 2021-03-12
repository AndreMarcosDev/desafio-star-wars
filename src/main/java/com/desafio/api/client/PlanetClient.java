package com.desafio.api.client;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Component
public class PlanetClient {
	
	public ResponseEntity<RequestWrapper> getPlanetByName(String nome) throws Exception {

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<String> entity = new HttpEntity<String>(constructHeaders());
		ResponseEntity<RequestWrapper> wrapper = null;

		try {

			wrapper = restTemplate.exchange("https://swapi.dev/api/planets/?search=" + nome, HttpMethod.GET, entity,
					RequestWrapper.class);

		} catch (HttpStatusCodeException e) {
			throw new Exception("Erro chamando serviço da api star wars: " + e);
		}

		return wrapper;
	}

	private HttpHeaders constructHeaders() {

		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.USER_AGENT, "");
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		return headers;
	}

}
