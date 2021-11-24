package com.mercadolibre.demo.dto;

import com.mercadolibre.demo.model.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {

    private PaymentStatus paymentStatus;
    private Double valueOfCart;
    private Long installment;

}
