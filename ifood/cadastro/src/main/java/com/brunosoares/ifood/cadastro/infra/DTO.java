package com.brunosoares.ifood.cadastro.infra;

import javax.validation.ConstraintValidatorContext;

public interface DTO {

    default boolean isValid(final ConstraintValidatorContext constraintValidatorContext) {
        return true;
    }

}
