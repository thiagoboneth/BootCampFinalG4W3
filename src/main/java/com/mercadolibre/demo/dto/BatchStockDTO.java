package com.mercadolibre.demo.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BatchStockDTO {

	@NotNull(message = "{currentTemperature.not.null}")
    private Float currentTemperature;

	@NotNull(message = "{minimumTemperature.not.null}")
    private Float minimumTemperature;

	@NotNull(message = "{initialQuantity.not.null}")
    private Long initialQuantity;

	@NotNull(message = "{currentQuantity.not.null}")
    private Long currentQuantity;
    
    private LocalDate manufacturingDate = LocalDate.now();
    private LocalDateTime manufacturingTime = LocalDateTime.now();
    private LocalDate dueDate = LocalDate.now();
    
    @NotNull(message = "{id.not.null}")
    private Long idSalesAd;
}
