package com.mercadolibre.demo.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class DelegateDTO {

    @NotBlank(message = "{name.not.blank}")
    private String name;

    @NotBlank(message = "{lastname.not.blank}")
    private String lastname;

    @NotNull(message = "{id.not.null}")
    private Long idSection;
}
