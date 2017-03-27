package com.twogether.backend.api.domain.util;

/**
 * Object que le retourne de l'api quand on a créé un objet
 */
public class CreatedObjectResponse {
	private Long id;
	
	private Integer version;

	private String errorMessage;
	
	public CreatedObjectResponse() {
	}	
	public CreatedObjectResponse(Long id, Integer version) {
		this.id = id;
		this.version = version;
	}
	
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public CreatedObjectResponse(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
