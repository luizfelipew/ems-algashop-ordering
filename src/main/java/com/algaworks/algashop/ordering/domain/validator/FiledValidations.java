package com.algaworks.algashop.ordering.domain.validator;

import org.apache.commons.validator.routines.EmailValidator;

import java.util.Objects;

public class FiledValidations {
    private FiledValidations() {
    }

    public static void requiresValidEmail(final String email) {
        requiresValidEmail(email, null);
    }

    public static void requiresValidEmail(final String email, final String errorMessage) {
        Objects.requireNonNull(email, errorMessage);
        if(email.isBlank()){
            throw new IllegalArgumentException(errorMessage);
        }
        if(!EmailValidator.getInstance().isValid(email)){
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
