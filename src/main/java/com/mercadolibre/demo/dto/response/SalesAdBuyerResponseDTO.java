package com.mercadolibre.demo.dto.response;

import com.mercadolibre.demo.model.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class SalesAdBuyerResponseDTO {

    private SalesAd salesAd;
    private Long quantity;
    
    public static SalesAdBuyerResponseDTO convertDTO(ItemOfProduct itemOfProduct) {
    	return new SalesAdBuyerResponseDTO(itemOfProduct.getSalesAd(), itemOfProduct.getQuantity());
    }
}
