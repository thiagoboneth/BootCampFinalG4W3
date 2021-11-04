package com.mercadolibre.demo.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
public class InboundOrderDTO {

	@NotBlank(message = "{orderDate.not.blank}")
    private LocalDate orderDate = LocalDate.now();

    private Long idBatchStock;

    private Long idSection;

}
