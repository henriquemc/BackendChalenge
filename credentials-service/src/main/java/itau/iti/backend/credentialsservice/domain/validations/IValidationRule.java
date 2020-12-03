package itau.iti.backend.credentialsservice.domain.validations;

import java.util.function.Predicate;

public interface IValidationRule extends Predicate<String> {

    String getCode();
}
