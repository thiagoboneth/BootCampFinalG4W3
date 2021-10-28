package com.mercadolibre.demo.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.mercadolibre.demo.model.BatchStock;
import com.mercadolibre.demo.model.InboundOrder;
import com.mercadolibre.demo.model.Section;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InboundOrderDTO {

	@NotBlank(message = "{orderDate.not.blank}")
    private LocalDate orderDate = LocalDate.now();

    private Long idBatchStock;

    private Long idSection;

}
