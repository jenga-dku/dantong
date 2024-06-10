package org.jenga.dantong.user.model.entity.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MajorValidator implements ConstraintValidator<Enum, java.lang.Enum> {

    @Override
    public boolean isValid(java.lang.Enum value, ConstraintValidatorContext context) {
        return value != null;
    }
}
