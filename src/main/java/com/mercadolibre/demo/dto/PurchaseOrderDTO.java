package com.mercadolibre.demo.dto;

import com.mercadolibre.demo.model.OrderStatus;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
public class PurchaseOrderDTO {
    @NotBlank(message = "{date.not.blank}")//colocar adv de fail date
    private LocalDate date = LocalDate.now();
    @NotBlank(message = "{status.not.blank}")//colocar adv fail status
    private OrderStatus orderStatus;
    @NotBlank(message = "{buyer.not.blank}")//colocar adv list fail
    private Long buyer;
}
