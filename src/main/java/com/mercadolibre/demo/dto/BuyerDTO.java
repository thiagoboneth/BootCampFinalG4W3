package com.mercadolibre.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
@Setter
@Getter
public class BuyerDTO {
    @NotBlank(message = "{name.not.blank}")
    private String name;
    @NotBlank(message = "{lastname.not.blank}")
    private String lastname;
}
