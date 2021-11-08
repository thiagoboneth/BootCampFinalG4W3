package com.mercadolibre.demo.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;

import lombok.Setter;

@Getter
@Setter
public class SalesAdDTO {

    private Float volume;
    private Float minimumTemperature;
    private Float maximumTemperature;
    private Double price;
    private Long sellerCode;
    private Long productCode;
}
