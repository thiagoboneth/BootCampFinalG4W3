package com.mercadolibre.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class AdressDTO {

   @NotBlank(message = "{cep.not.blank}")
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    @NotBlank(message = "{localidade.not.blank}")
    private String localidade;
    @NotBlank(message = "{uf.not.blank}")
    private String uf;
    @NotBlank(message = "{cellFone.not.blank}")
    private String fone1;
    @NotBlank(message = "{cellFone.not.blank}")
    private String fone2;
    @NotNull(message = "{id.not.null}")
    private Long Idbuyer;
    private String Referencia;
}
