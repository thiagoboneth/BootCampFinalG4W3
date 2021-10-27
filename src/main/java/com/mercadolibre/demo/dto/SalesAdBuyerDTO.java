package com.mercadolibre.demo.dto;

import com.mercadolibre.demo.model.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class SalesAdBuyerDTO {

	private SalesAd salesAd;
	private int quantity;

	public SalesAdBuyer convertObject() {
		return new SalesAdBuyer(quantity, salesAd);
	}
}
