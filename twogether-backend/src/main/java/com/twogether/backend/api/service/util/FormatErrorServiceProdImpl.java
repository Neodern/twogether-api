package com.twogether.backend.api.service.util;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Primary
@Profile("prod")
public class FormatErrorServiceProdImpl implements FormatErrorService {

	@Override
	public String convertExceptionToErrorMessage(Exception e) {
		return "server side error";
	}
}
