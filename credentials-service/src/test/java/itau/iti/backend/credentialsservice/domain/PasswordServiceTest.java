package itau.iti.backend.credentialsservice.domain;

import itau.iti.backend.credentialsservice.domain.exceptions.ValidationException;
import itau.iti.backend.credentialsservice.domain.services.IValidatePasswordUseCase;
import itau.iti.backend.credentialsservice.domain.services.PasswordService;
import itau.iti.backend.credentialsservice.domain.validations.IValidationRule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PasswordServiceTest {

    public static final String ANY_STRING = "anyString";

    @Test
    void testValidations() throws ValidationException {

        //Setup
        IValidationRule validation = mock(IValidationRule.class);

        when(validation.test(ANY_STRING)).thenReturn(true).thenReturn(true);

        List<IValidationRule> validations = Arrays.asList(validation, validation);

        IValidatePasswordUseCase service = new PasswordService(validations);

        //Replay
        service.validate(ANY_STRING);

        //Verify
        verify(validation, times(2)).test(ANY_STRING);

    }

    @Test
    void testValidateWhenThrowsException() {

        //Setup
        IValidationRule validation = mock(IValidationRule.class);

        when(validation.test(ANY_STRING)).thenReturn(true).thenReturn(false);
        when(validation.getCode()).thenReturn("REASON");

        List<IValidationRule> validations = Arrays.asList(validation, validation);

        IValidatePasswordUseCase service = new PasswordService(validations);

        //Replay
        ValidationException exception = assertThrows(ValidationException.class, () ->
                service.validate(ANY_STRING));

        //Verify
        assertEquals("InvalidPassword", exception.getMessage());
        assertEquals("[REASON]", exception.getCodes().toString());

        verify(validation, times(2)).test(ANY_STRING);
        verify(validation, times(3)).getCode();
    }
}
