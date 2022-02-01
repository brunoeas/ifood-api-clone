package com.brunosoares.ifood.cadastro.infra;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.validation.ConstraintViolation;
import java.io.Serializable;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@ToString
@Getter
public class ConstraintViolationImpl implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "Path do atributo, ex dataInicio, pessoa.endereco.numero")
    private final String atributo;

    @Schema(description = "Mensagem descritiva do erro possivelmente associado ao path", required = true)
    private final String mensagem;

    private ConstraintViolationImpl(final ConstraintViolation<?> violation) {
        this.mensagem = violation.getMessage();
        this.atributo = Stream.of(violation.getPropertyPath().toString().split("\\."))
                .skip(2)
                .collect(Collectors.joining("."));
    }

    public static ConstraintViolationImpl of(final ConstraintViolation<?> violation) {
        return new ConstraintViolationImpl(violation);
    }

    public static ConstraintViolationImpl of(final String violation) {
        return new ConstraintViolationImpl(null, violation);
    }

}
