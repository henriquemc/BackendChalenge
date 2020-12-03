package itau.iti.backend.credentialsservice.domain.validations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MinLowerCaseRule implements IValidationRule {

    private Logger logger = LoggerFactory.getLogger(MinLowerCaseRule.class);

    @Override
    public boolean test(String password) {
        return password.matches(".*[a-z].*");
    }

    @Override
    public String getCode() {
        return "MinLowerCase";
    }
}
