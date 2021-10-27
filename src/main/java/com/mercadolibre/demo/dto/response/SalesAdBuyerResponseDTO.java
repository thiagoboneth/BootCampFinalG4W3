package com.mercadolibre.demo.dto.response;

import com.mercadolibre.demo.model.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class SalesAdBuyerResponseDTO {

    private SalesAd salesAd;
    private int quantity;
    
    public static SalesAdBuyerResponseDTO convertDTO(SalesAdBuyer salesAdBuyer) {
    	return new SalesAdBuyerResponseDTO(salesAdBuyer.getSalesAd(), salesAdBuyer.getQuantity());
    }
}
