package com.mercadolibre.demo.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InboundOrderDTO {

    private LocalDate orderDate = LocalDate.now();

	@NotNull(message = "{id.not.null}")
    private Long idBatchStock;

	@NotNull(message = "{id.not.null}")
    private Long idSection;
}
