package com.mercadolibre.demo.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
    @NotBlank(message = "{manufacturingDate.not.blank}")
    private LocalDate manufacturingDate = LocalDate.now();
    @NotBlank(message = "{manufacturingTime.not.blank}")
    private LocalDateTime manufacturingTime = LocalDateTime.now();
    @NotBlank(message = "{dueDate.not.blank}")
    private LocalDate dueDate = LocalDate.now();
    private Long salesadId;

}
