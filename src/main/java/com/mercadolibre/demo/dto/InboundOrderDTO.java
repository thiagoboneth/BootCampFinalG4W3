package com.mercadolibre.demo.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InboundOrderDTO {

    private LocalDate orderDate = LocalDate.now();

    private Long idBatchStock;

    private Long idSection;

}
