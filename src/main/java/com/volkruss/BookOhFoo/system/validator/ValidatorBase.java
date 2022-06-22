package com.volkruss.BookOhFoo.system.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public abstract class ValidatorBase<T> implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    public void validate(Object target, Errors errors) {
        vaild((T) target, errors);
    }

    public abstract void vaild(final T form, final Errors errors);
}
