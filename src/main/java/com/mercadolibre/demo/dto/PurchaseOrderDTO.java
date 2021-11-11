package com.mercadolibre.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

import javax.validation.constraints.NotNull;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PurchaseOrderDTO {
  
    @NotNull(message = "{id.not.null}")
    private Long idBuyer;
   
    private List<ItemOfProduct2DTO> itemOfProduct;

}
