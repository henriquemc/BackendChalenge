package itau.iti.backend.credentialsservice.adapters.web.apis;

import itau.iti.backend.credentialsservice.adapters.web.models.ValidationResult;
import itau.iti.backend.credentialsservice.domain.exceptions.ValidationException;
import itau.iti.backend.credentialsservice.domain.services.IValidatePasswordUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
public class CredentialsAPI {

    private IValidatePasswordUseCase useCase;

    public CredentialsAPI(IValidatePasswordUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping(path = "/validate")
    ResponseEntity<ValidationResult> validate(@RequestBody(required = false) String password) throws ValidationException {
        if (password == null) {
            throw new ValidationException("Password must be provided.");
        }

        useCase.validate(password);

        return new ResponseEntity<ValidationResult>(new ValidationResult(true), HttpStatus.OK);
    }

}
