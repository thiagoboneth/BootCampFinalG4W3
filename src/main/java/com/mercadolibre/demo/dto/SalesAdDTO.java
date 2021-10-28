package com.mercadolibre.demo.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.mercadolibre.demo.model.Product;
import com.mercadolibre.demo.model.SalesAd;
import com.mercadolibre.demo.model.Seller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalesAdDTO {

	@NotBlank(message = "{volume.not.blank}")
    private Float volume;
	@NotBlank(message = "{minimumTemperature.not.blank}")
    private Float minimumTemperature;
	@NotBlank(message = "{maximumTemperature.not.blank}")
    private Float maximumTemperature;
	@NotBlank(message = "{price.not.blank}")
    private Double price;
    private Long seller_code;
    private Long product_code;
}
