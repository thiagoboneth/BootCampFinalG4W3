package com.mercadolibre.demo.dto;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalesAdDTO {

	@NotNull(message = "{volume.not.blank}")
    private Float volume;
	
	@NotNull(message = "{minimumTemperature.not.null}")
    private Float minimumTemperature;
    
	@NotNull(message = "{maximumTemperature.not.null}")
    private Float maximumTemperature;
	
	@NotNull(message = "{price.not.null}")
    private Double price;
	
    @NotNull(message = "{id.not.null}")
    private Long idSeller;
    
    @NotNull(message = "{id.not.null}")
    private Long idProduct;
}
