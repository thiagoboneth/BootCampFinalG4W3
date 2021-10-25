package com.mercadolibre.demo.dto;

import com.mercadolibre.demo.model.*;
import lombok.Getter;

@Getter
public class SalesAdBuyerDTO {

    private SalesAd salesad;
    private Buyer buyer;
	
	
	public SalesAdBuyer convertObject() {
		return new SalesAdBuyer(salesad, buyer);
	}
}
