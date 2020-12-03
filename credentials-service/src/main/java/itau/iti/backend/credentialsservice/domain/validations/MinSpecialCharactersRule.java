package itau.iti.backend.credentialsservice.domain.validations;

import java.util.stream.Stream;

public class MinSpecialCharactersRule implements IValidationRule {

    public static final String SPECIAL_CHARS = "!@#$%^&*()-+";

    @Override
    public boolean test(String password) {
        Stream<Character> characterStream = SPECIAL_CHARS.chars()
                .mapToObj(c -> (char) c);

        return characterStream.anyMatch(c -> password.contains(c.toString()));
    }

    @Override
    public String getCode() {
        return "MinSpecialCharacters";
    }
}
