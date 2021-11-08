package com.mercadolibre.demo.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BatchStockDTO {


    private Float currentTemperature;

    private Float minimumTemperature;

    private Long initialQuantity;

    private Long currentQuantity;
    
    private LocalDate manufacturingDate = LocalDate.now();
    private LocalDateTime manufacturingTime = LocalDateTime.now();
    private LocalDate dueDate;
    private Long idSalesAd;
}
