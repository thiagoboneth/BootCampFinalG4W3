package com.mercadolibre.demo.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SalesAdDTO {

	@NotBlank(message = "{volume.not.blank}")
    private Float volume;
	@NotBlank(message = "{minimumTemperature.not.blank}")
    private Float minimumTemperature;
	@NotBlank(message = "{maximumTemperature.not.blank}")
    private Float maximumTemperature;
	@NotBlank(message = "{price.not.blank}")
    private Double price;
    private Long idSeller;
    private Long idProduct;
}
