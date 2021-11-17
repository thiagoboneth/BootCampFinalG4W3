package com.mercadolibre.demo.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class StockByWareHouseDTO {
    private String categoria;
    private Long currentQuantity;
    private Double price;
    private String warehouseName;
    private String name;
}