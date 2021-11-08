package com.mercadolibre.demo.dto.response;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class PriceDTO {
    @NotBlank(message = "{totalPrice.not.blank}")
    private Double totalPrice;
}
