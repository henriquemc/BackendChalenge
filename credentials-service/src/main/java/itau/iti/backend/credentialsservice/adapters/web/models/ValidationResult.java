package itau.iti.backend.credentialsservice.adapters.web.models;

import lombok.Getter;

public class ValidationResult {
    @Getter
    boolean result;
    @Getter
    String additionalInfo;

    public ValidationResult(boolean result) {
        this(true, null);
    }

    public ValidationResult(boolean result, String additionalInfo) {
        this.additionalInfo = additionalInfo;
        this.result = result;
    }


}
