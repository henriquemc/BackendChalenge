package itau.iti.backend.credentialsservice.domain.validations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WithoutRepetitionsRule implements IValidationRule {

    private Logger logger = LoggerFactory.getLogger(WithoutRepetitionsRule.class);


    @Override
    public boolean test(String password) {
        char[] charArray = password.toCharArray();

        if (charArray.length <= 1) {
            logger.info("small password. returning that is valid.");
            return true;
        }

        int arrayLimit = charArray.length - 1;
        for (int i = 0; i < arrayLimit; i++) {
            if (i + 1 < arrayLimit && charArray[i] == charArray[i + 1]) {
                logger.info("repetition founded. Returning password invalid.");
                return false;
            }
        }
        logger.info("without repetitions. Returning that password is valid.");
        return true;
    }

    @Override
    public String getCode() {
        return "WithoutRepetition";
    }
}
