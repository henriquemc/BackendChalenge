package itau.iti.backend.credentialsservice.domain.services;


import itau.iti.backend.credentialsservice.domain.exceptions.ValidationException;

public interface IValidatePasswordUseCase {
    void validate(String password) throws ValidationException;
}
