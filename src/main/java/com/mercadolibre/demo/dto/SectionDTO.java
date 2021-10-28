package com.mercadolibre.demo.dto;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class SectionDTO {

    @NotBlank(message = "{capacity.not.blank}")
    private Long capacity;

    private Long idWareHouse;
}
