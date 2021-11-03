package com.mercadolibre.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class SectionDTO {

    @NotBlank(message = "{capacity.not.blank}")
    private Long capacity;

    private String category;

    private Long idWareHouse;
}
