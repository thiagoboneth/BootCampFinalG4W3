package com.mercadolibre.demo.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SectionDTO {

	@NotNull(message = "{capacity.not.null}")
    private Long capacity;

	@NotBlank(message = "{category.not.blank}")
    private String category;

	@NotNull(message = "{id.not.null}")
    private Long idWareHouse;
}
