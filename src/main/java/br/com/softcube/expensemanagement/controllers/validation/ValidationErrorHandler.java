package br.com.softcube.expensemanagement.controllers.validation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.softcube.expensemanagement.models.dtos.FormErrorMessageDTO;

@RestControllerAdvice
public class ValidationErrorHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public List<FormErrorMessageDTO> handleError(MethodArgumentNotValidException exception) {
		return exception.getBindingResult().getFieldErrors()
				.stream()
				.map(fieldError -> createFormErrorMessageDTO(fieldError))
				.collect(Collectors.toList());
	}

	private FormErrorMessageDTO createFormErrorMessageDTO(FieldError fieldError) {
		return new FormErrorMessageDTO(fieldError.getField(), getErrorMessageUsingLocale(fieldError));
	}

	private String getErrorMessageUsingLocale(FieldError fieldError) {
		return messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
	}

}
