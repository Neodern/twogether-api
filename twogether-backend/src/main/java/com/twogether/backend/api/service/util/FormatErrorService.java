package com.twogether.backend.api.service.util;

/** Service qui permet de créer un message d'erreur (string) à partir d'une exception */
public interface FormatErrorService {
	String convertExceptionToErrorMessage(Exception e);
}
