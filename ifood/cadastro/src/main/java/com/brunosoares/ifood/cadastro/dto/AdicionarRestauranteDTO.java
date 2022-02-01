package com.brunosoares.ifood.cadastro.dto;

import com.brunosoares.ifood.cadastro.infra.DTO;
import com.brunosoares.ifood.cadastro.infra.ValidDTO;
import com.brunosoares.ifood.cadastro.orm.Restaurante;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@ValidDTO
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AdicionarRestauranteDTO implements DTO, Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 120)
    private String proprietario;

    @NotNull
    @Size(min = 18, max = 18)
    @CNPJ
    private String nrCnpj;

    @NotNull
    @Size(max = 120)
    private String nome;

    @NotNull
    private LocalizacaoDTO localizacao;

    @Override
    public boolean isValid(final ConstraintValidatorContext constraintValidatorContext) {
        constraintValidatorContext.disableDefaultConstraintViolation();
        if (Restaurante.find("nrCnpj", this.nrCnpj).count() > 0) {
            constraintValidatorContext.buildConstraintViolationWithTemplate("CNPJ já cadastrado")
                    .addPropertyNode("cnpj")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }

}