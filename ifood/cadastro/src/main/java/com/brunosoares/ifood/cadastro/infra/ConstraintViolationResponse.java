package com.brunosoares.ifood.cadastro.infra;

import lombok.Getter;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ConstraintViolationResponse {

    private final List<ConstraintViolationImpl> violacoes = new ArrayList<>();

    private ConstraintViolationResponse(final ConstraintViolationException exception) {
        exception.getConstraintViolations()
                .forEach(violation -> this.violacoes.add(ConstraintViolationImpl.of(violation)));
    }

    public static ConstraintViolationResponse of(final ConstraintViolationException exception) {
        return new ConstraintViolationResponse(exception);
    }

}
