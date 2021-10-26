package com.mercadolibre.demo.dto.response;

import com.mercadolibre.demo.model.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class SalesAdBuyerResponseDTO {
	
    private SalesAd salesad;
    private Buyer buyer;
    
//    public static SalesAdBuyerResponseDTO convertDTO(SalesAdBuyer salesAdBuyer) {
//    	return new SalesAdBuyerResponseDTO(salesAdBuyer.getSalesad(), salesAdBuyer.getBuyer());
//    }
}
