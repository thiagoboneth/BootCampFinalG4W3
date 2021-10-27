package com.mercadolibre.demo.dto;

import lombok.Getter;
import javax.validation.constraints.NotBlank;

@Getter
public class SellerDTO {

    @NotBlank(message = "{name.not.blank}")
    private String name;
    @NotBlank(message = "{lastname.not.blank}")
    private String lastname;
}
