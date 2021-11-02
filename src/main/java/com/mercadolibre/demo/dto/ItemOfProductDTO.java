package com.mercadolibre.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class ItemOfProductDTO {

    @NotBlank(message = "{quantity.not.blank}")
    private Long quantity;

    private Long idsalesAd;
}
