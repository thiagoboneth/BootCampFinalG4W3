package com.mercadolibre.demo.dto;

import com.mercadolibre.demo.model.SalesAd;
import lombok.Data;

@Data
public class ItemOfProductDTO {

        private Long quantity;
        private SalesAd salesAd;
}
