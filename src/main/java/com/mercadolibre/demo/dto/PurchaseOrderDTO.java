package com.mercadolibre.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mercadolibre.demo.model.Buyer;
import com.mercadolibre.demo.model.ItemOfProduct;
import com.mercadolibre.demo.model.OrderStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PurchaseOrderDTO {

    private Long buyer;
    private List<ItemOfProductDTO> itemOfProduct;

}
