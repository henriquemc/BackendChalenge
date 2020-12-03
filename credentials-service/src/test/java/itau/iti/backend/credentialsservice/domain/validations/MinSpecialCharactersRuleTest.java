package itau.iti.backend.credentialsservice.domain.validations;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MinSpecialCharactersRuleTest {

    @DisplayName("Min 1 special character. !@#$%^&*()-+")
    @ParameterizedTest(name = "Must contains 1 special character.")
    @ValueSource(strings = {"!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "-", "+", "AbTp9!fok"})
    void testMinSpecialChars(String value) {
        boolean result = new MinSpecialCharactersRule().test(value);
        assertTrue(result, "Must be true when exists at least one special character");
    }


    @DisplayName("Min 1 special character. Invalid")
    @ParameterizedTest(name = "Invalid values")
    @ValueSource(strings = {"aB", "1", "}"})
    void testMinSpecialCharsInvalid(String value) {
        boolean result = new MinSpecialCharactersRule().test(value);
        assertFalse(result, "Must be false when not exists at least one special character");
    }

}