package com.mercadolibre.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PurchaseOrderDTO {

    private Long idBuyer;
    private List<ItemOfProductDTO> itemOfProduct;

}
