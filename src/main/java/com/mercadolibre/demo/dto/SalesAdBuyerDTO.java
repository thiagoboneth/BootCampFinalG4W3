package com.mercadolibre.demo.dto;

import com.mercadolibre.demo.model.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SalesAdBuyerDTO {

	private SalesAd salesAd;
	private Long quantity;

}
