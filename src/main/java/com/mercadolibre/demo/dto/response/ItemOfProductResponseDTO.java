package com.mercadolibre.demo.dto.response;

import com.mercadolibre.demo.model.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ItemOfProductResponseDTO {

    private SalesAd salesAd;
    private int quantity;
}
