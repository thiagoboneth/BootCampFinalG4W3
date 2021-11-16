package com.mercadolibre.demo.dto;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SectionTypeDTO {

    private String name;
    private Long quantity;
    private Double price;
    private String wareHouse;
    private String nameProduct;

}
