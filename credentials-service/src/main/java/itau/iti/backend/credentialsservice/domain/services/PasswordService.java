package itau.iti.backend.credentialsservice.domain.services;

import itau.iti.backend.credentialsservice.domain.exceptions.ValidationException;
import itau.iti.backend.credentialsservice.domain.validations.IValidationRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class PasswordService implements IValidatePasswordUseCase {

    private final List<IValidationRule> validationStream;
    private Logger logger = LoggerFactory.getLogger(PasswordService.class);

    public PasswordService(List<IValidationRule> validations) {
        this.validationStream = validations;
    }

    @Override
    public void validate(String password) throws ValidationException {
        List<String> codes = new ArrayList<>();
        boolean result = validationStream.stream().allMatch(v -> {
            boolean valid = v.test(password);

            if (!valid) {
                codes.add(v.getCode());
            }

            logger.info("Validating " + password + " with " + v.getCode() + "Result:" + valid + "");
            return valid;
        });

        if (!result) {
            throw new ValidationException("InvalidPassword", codes);
        }
    }
}
