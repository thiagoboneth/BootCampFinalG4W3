package com.mercadolibre.demo.dto;

import com.mercadolibre.demo.model.*;
import lombok.Getter;

import java.util.List;

@Getter
public class SalesAdBuyerDTO {

    private List<SalesAd> salesad;
    private Buyer buyer;
	
	
//	public SalesAdBuyer convertObject() {
//		return new SalesAdBuyer(salesad, buyer);
//	}
}
