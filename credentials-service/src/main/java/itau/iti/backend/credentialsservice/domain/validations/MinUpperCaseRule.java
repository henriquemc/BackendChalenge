package itau.iti.backend.credentialsservice.domain.validations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MinUpperCaseRule implements IValidationRule {

    private Logger logger = LoggerFactory.getLogger(MinUpperCaseRule.class);


    @Override
    public boolean test(String password) {
        return password.matches(".*[A-Z].*");
    }

    @Override
    public String getCode() {
        return "MinUpper";
    }
}
