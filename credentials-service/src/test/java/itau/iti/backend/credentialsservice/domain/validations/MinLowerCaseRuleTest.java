package itau.iti.backend.credentialsservice.domain.validations;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MinLowerCaseRuleTest {


    @DisplayName("Min 1 lowercase - Valid")
    @ParameterizedTest(name = "Must contains 1 lowercase character.")
    @ValueSource(strings = {"aA", "Aa", "AbTp9!fok"})
    void testMinLowerCase(String value) {
        boolean result = new MinLowerCaseRule().test(value);
        assertTrue(result, "Must be true when exists at least one lowercase character");
    }

    @DisplayName("Min 1 lowercase - Invalid")
    @ParameterizedTest(name = "Must contains 1 lowercase character.")
    @ValueSource(strings = {"A", "AAA", "$DSA", "1"})
    void testMinLowerCaseInvalid(String value) {
        boolean result = new MinLowerCaseRule().test(value);
        assertFalse(result, "Must be false when not exists at least one lowercase character");
    }

}