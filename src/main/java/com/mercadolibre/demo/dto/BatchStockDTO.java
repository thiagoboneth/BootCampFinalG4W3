package com.mercadolibre.demo.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BatchStockDTO {

    @NotBlank(message = "{currentTemperature.not.blank}")
    private Float currentTemperature;
    
    @NotBlank(message = "{minimumTemperature.not.blank}")
    private Float minimumTemperature;
    
    @NotBlank(message = "{initialQuantity.not.blank}")
    private Long initialQuantity;
    
    @NotBlank(message = "{currentQuantity.not.blank}")
    private Long currentQuantity;
    
    private LocalDate manufacturingDate = LocalDate.now();
    private LocalDateTime manufacturingTime = LocalDateTime.now();
    private LocalDate dueDate = LocalDate.now();
    private Long IdSalesAd;
}
