package com.mercadolibre.demo.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductInBatchStockDTO {
    private String nameProduct;
    private Long batchStock;
}
