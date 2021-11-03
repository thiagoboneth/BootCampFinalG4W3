package com.mercadolibre.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor

public class SectionTypeDTO {

    private String name;
    private Long quantity;
    private Double price;
    private String wareHouse;
    private String nameProduct;

    public SectionTypeDTO() {
    }
}
