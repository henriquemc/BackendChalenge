package itau.iti.backend.credentialsservice.domain.validations;

public class MinLengthRule implements IValidationRule {
    @Override
    public boolean test(String password) {
        boolean result = password.length() >= 9;
        return result;
    }

    @Override
    public String getCode() {
        return "Min length";
    }
}
