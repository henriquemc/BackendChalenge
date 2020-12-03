package itau.iti.backend.credentialsservice.domain.validations;

public class MinDigitsRule implements IValidationRule {

    @Override
    public boolean test(String password) {
        boolean result = password.matches(".*\\d.*");
        return result;
    }

    @Override
    public String getCode() {
        return "MinDigits";
    }
}
