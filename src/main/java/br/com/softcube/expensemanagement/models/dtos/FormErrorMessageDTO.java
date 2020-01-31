package br.com.softcube.gestaofinanceira.models.dtos;

public class FormErrorMessageDTO {
	
	private String field;
	private String message;
	
	public FormErrorMessageDTO(String field, String message) {
		this.field = field;
		this.message = message;
	}

	public String getField() {
		return field;
	}

	public String getMessage() {
		return message;
	}
	
}