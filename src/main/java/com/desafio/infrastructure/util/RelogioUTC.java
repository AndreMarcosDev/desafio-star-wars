package com.desafio.infrastructure.util;

import java.time.OffsetDateTime;

import org.springframework.stereotype.Component;

@Component
public class RelogioUTC implements Relogio { 

	@Override
	public OffsetDateTime getTimeNow() {
		return OffsetDateTime.now().withNano(0);
	}

}
