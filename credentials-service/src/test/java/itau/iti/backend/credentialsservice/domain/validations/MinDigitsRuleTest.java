package itau.iti.backend.credentialsservice.domain.validations;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MinDigitsRuleTest {

    @DisplayName("9 or more chars - Valid")
    @ParameterizedTest(name = "Must contains 9 or more chars.")
    @ValueSource(strings = {"123456789", "123456789a", "AbTp9!fok"})
    void testMinLengthValid(String value) {
        boolean result = new MinDigitsRule().test(value);
        assertTrue(result, "Must be true when string has 9 or more chars");
    }

    @DisplayName("9 or more chars - Less than 9")
    @ParameterizedTest(name = "Must be invalid when is lower then 9 chars")
    @ValueSource(strings = {"12345678", ""})
    void testMinLengthInvalid(String value) {
        boolean result = new MinLengthRule().test(value);
        assertFalse(result, "Only more than 9 chars is valid.");
    }
}