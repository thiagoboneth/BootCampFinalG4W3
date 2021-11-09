package com.mercadolibre.demo.dto.response;


import lombok.Data;

@Data
public class WareHouseProductListDTO {

    private Long quantity;
    private String wareHouseName;

    public WareHouseProductListDTO() {}

}