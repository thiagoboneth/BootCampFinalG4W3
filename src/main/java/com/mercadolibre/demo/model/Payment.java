package com.mercadolibre.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payment")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Payment {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPayment")
    private Long idPayment;

    @Column(name = "paymentMethod", nullable = false)
    private PaymentStatus paymentStatus = PaymentStatus.A_VISTA_DINHEIRO;

    @Column(name = "valueofCart", nullable = false)
    private Double valueOfCart;

    @Column(name = "installment", nullable = false)
    private Long installment;

    @Column(name = "numeroBoleto")
    private String numeroBoleto;

    public Payment(PaymentStatus paymentStatus, Double valueOfCart, Long installment) {
        this.paymentStatus = paymentStatus;
        this.valueOfCart = valueOfCart;
        this.installment = installment;
    }

    public Payment(PaymentStatus paymentStatus, Double valueOfCart, Long installment, String numeroBoleto) {
        this.paymentStatus = paymentStatus;
        this.valueOfCart = valueOfCart;
        this.installment = installment;
        this.numeroBoleto = numeroBoleto;
    }
}
