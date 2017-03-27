package com.twogether.backend.api.service.util;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnMissingBean(FormatErrorServiceProdImpl.class)
public class FormatErrorServiceDebugImpl implements FormatErrorService {

	@Override
	public String convertExceptionToErrorMessage(Exception e) {
		return e.getMessage();
	}

}
