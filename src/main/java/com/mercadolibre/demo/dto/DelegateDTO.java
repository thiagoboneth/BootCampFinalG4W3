package com.mercadolibre.demo.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class DelegateDTO {

    private String name;

    private String lastname;

    private Long idSection;
}
