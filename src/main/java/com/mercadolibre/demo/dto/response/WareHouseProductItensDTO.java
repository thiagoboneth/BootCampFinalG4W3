package com.mercadolibre.demo.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class WareHouseProductItensDTO {

    private long idProduct;
    private List<WareHouseProductListDTO> list;
    public WareHouseProductItensDTO() {}

}