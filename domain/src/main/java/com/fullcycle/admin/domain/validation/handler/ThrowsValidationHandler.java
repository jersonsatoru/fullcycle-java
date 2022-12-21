package com.fullcycle.admin.domain.validation.handler;

import java.util.List;

import com.fullcycle.admin.domain.exceptions.DomainException;
import com.fullcycle.admin.domain.validation.Error;
import com.fullcycle.admin.domain.validation.ValidationHandler;

public class ThrowsValidationHandler implements ValidationHandler {
    @Override
    public ValidationHandler append(final Error anError) {
        throw DomainException.with(anError);
    }

    @Override
    public ValidationHandler append(final ValidationHandler anHandler) {
        throw DomainException.with(anHandler.getErrors());
    }

    @Override
    public ValidationHandler validate(final Validation aValidation) {
        try {
            aValidation.validate();
        } catch (final Exception e) {
            throw DomainException.with(new Error(e.getMessage()));
        }
        
        return this;
    }

    @Override
    public List<Error> getErrors() {
        return List.of();
    }
    
}
