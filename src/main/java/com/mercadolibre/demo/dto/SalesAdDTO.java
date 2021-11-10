package com.mercadolibre.demo.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalesAdDTO {

	//@NotBlank(message = "{volume.not.blank}")
    private Float volume;
	
//	@NotBlank(message = "{minimumTemperature.not.blank}")
    private Float minimumTemperature;
    
//	@NotBlank(message = "{maximumTemperature.not.blank}")
    private Float maximumTemperature;
	
	//@NotBlank(message = "{price.not.blank}")
    private Double price;
	
    private Long idSeller;
    private Long idProduct;
}