package com.mercadolibre.demo.dto;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class SectionDTO {

    private Long capacity;

    private String category;

    private Long idWareHouse;
}
