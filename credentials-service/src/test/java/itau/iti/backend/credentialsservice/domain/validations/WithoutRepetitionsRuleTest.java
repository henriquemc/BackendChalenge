package itau.iti.backend.credentialsservice.domain.validations;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WithoutRepetitionsRuleTest {

    @DisplayName("Not repeated chars - Valid")
    @ParameterizedTest(name = "Valid when not exists repetition.")
    @ValueSource(strings = {"!asd", "c!9", "AbTp9!fok"})
    void testNotRepeatedValid(String value) {
        boolean result = new WithoutRepetitionsRule().test(value);
        assertTrue(result, "Must be true when not exists repetition. Value: " + value);
    }

    @DisplayName("Not repeated chars - Invalid")
    @ParameterizedTest(name = "Must not contain repeated values.")
    @ValueSource(strings = {"!!", "a!!c"})
    void testNotRepeated(String value) {
        boolean result = new WithoutRepetitionsRule().test(value);
        assertFalse(result, "Must be false when exists repetition. Value: " + value);
    }
}