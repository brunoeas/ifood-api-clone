package com.brunosoares.ifood.cadastro.infra;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidDTOValidator implements ConstraintValidator<ValidDTO, DTO> {

    @Override
    public void initialize(final ValidDTO constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(final DTO dto, final ConstraintValidatorContext constraintValidatorContext) {
        return dto.isValid(constraintValidatorContext);
    }

}
