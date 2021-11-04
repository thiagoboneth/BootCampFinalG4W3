package com.mercadolibre.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

import com.mercadolibre.demo.model.SalesAd;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ItemOfProductDTO {

        private Long quantity;
        private Long salesAd;
        private String nameProduct;
}
