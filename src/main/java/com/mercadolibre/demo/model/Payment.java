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
    private PaymentStatus paymentStatus = PaymentStatus.EM_ANALISE;

    @Column(name = "valueofCart", nullable = false)
    private Long valueOfCart;

    @Column(name = "installment", nullable = false)
    private Long installment;

    public Payment(PaymentStatus paymentStatus, Long valueOfCart, Long installment) {
        this.paymentStatus = paymentStatus;
        this.valueOfCart = valueOfCart;
        this.installment = installment;
    }
}
