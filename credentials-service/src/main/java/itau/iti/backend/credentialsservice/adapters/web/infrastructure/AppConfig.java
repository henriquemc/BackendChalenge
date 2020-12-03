package itau.iti.backend.credentialsservice.adapters.web.infrastructure;

import itau.iti.backend.credentialsservice.domain.services.IValidatePasswordUseCase;
import itau.iti.backend.credentialsservice.domain.services.PasswordService;
import itau.iti.backend.credentialsservice.domain.validations.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class AppConfig {

    @Bean
    public IValidatePasswordUseCase passwordService() {
        return new PasswordService(Arrays.asList(new MinDigitsRule(), new MinLengthRule(),
                new MinLowerCaseRule(), new MinSpecialCharactersRule(),
                new MinUpperCaseRule(), new WithoutRepetitionsRule()));
    }
}