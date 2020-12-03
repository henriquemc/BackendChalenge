package itau.iti.backend.credentialsservice.domain.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationException extends Exception {
    private List<String> codes;

    public ValidationException(String msg) {
        this(msg, new ArrayList<>());
    }

    public ValidationException(String msg, List<String> codes) {
        super(msg);
        this.codes = new ArrayList<>(codes);
    }

    public List<String> getCodes() {
        return codes;
    }
}
