package com.mercadolibre.demo.dto.response;

import com.mercadolibre.demo.model.Product;
import com.mercadolibre.demo.model.SalesAd;
import com.mercadolibre.demo.model.Seller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class SalesAdResponseDTO {
	
    private Float volume;
    private Float minimumTemperature;
    private Float maximumTemperature;
    private Double price;
    private Seller seller;
    private Product product;
    
    
	public static SalesAdResponseDTO convertDTO(SalesAd salesAd) {
		return new SalesAdResponseDTO(salesAd.getVolume(), salesAd.getMinimumTemperature(),
				salesAd.getMaximumTemperature(), salesAd.getPrice(), salesAd.getSeller(), salesAd.getProduct());
	}
}
