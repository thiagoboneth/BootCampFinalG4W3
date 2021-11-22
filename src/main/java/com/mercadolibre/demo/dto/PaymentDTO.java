package com.mercadolibre.demo.dto;

import com.mercadolibre.demo.model.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PaymentDTO {

    private PaymentStatus paymentStatus;
    private Long valueOfCart;
    private Long installment;

}
