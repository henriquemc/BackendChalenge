package itau.iti.backend.credentialsservice.domain.validations;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MinUpperCaseRuleTest {

    @DisplayName("Min 1 uppercase")
    @ParameterizedTest(name = "Must contains 1 uppercase character.")
    @ValueSource(strings = {"aA", "Aa", "AbTp9!fok"})
    void testMinUpperCase(String value) {
        boolean result = new MinUpperCaseRule().test(value);
        assertTrue(result, "Must be true when exists at least one uppercase character");
    }


}