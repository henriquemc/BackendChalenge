package itau.iti.backend.credentialsservice.adapters.web.infrastructure;

import itau.iti.backend.credentialsservice.adapters.web.models.ValidationResult;
import itau.iti.backend.credentialsservice.domain.exceptions.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionAdvice {

	@ResponseBody
	@ExceptionHandler(ValidationException.class)
	ResponseEntity<ValidationResult> passwordInvalid(ValidationException ex) {
		String message = ex.getMessage() + " - Errors: " + ex.getCodes();
		return new ResponseEntity<ValidationResult>(new ValidationResult(false, message), HttpStatus.BAD_REQUEST);
	}

}
