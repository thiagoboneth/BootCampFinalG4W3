package com.mercadolibre.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductItenForCarsDTO {
    private long id_purchase_order;
    private List<ProductItenDTO> list;
}