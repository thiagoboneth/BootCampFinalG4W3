package com.mercadolibre.demo.controller;

import com.mercadolibre.demo.config.SecurityController;
import com.mercadolibre.demo.model.Payment;
import com.mercadolibre.demo.model.PaymentStatus;
import com.mercadolibre.demo.service.PaymentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/paymentMethod/")
public class PaymentsController implements SecurityController {


    @Autowired
    private PaymentsService paymentsService;

    @PostMapping(value = "/payment")
    @ResponseBody
    public ResponseEntity<Payment> payment(@Valid Long idCart, PaymentStatus paymentStatus, Long installment) throws Exception {
        Payment payment = paymentsService.payment(idCart, paymentStatus, installment);
        return new ResponseEntity<>(payment, HttpStatus.ACCEPTED);
    }
}
