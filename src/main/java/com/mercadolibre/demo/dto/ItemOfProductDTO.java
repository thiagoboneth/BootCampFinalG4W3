package com.mercadolibre.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ItemOfProductDTO {

	@NotNull(message = "{quantity.not.null}")
	private Long quantity;
	
	@NotNull(message = "{id.not.null}")
	private Long idSalesAd;
	
	@NotBlank(message = "{name.not.blank}")
	private String nameProduct;
}