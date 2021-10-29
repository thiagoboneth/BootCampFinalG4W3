package com.mercadolibre.demo.dto;

import com.mercadolibre.demo.model.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
public class ItemOfProductDTO {

    @NotBlank(message = "{quantity.not.blank}")
    private Long quantity;

    private Long idsalesAd;
}
