package com.mercadolibre.demo.controller;

import com.mercadolibre.demo.config.SecurityController;
import com.mercadolibre.demo.dto.PaymentDTO;
import com.mercadolibre.demo.model.Payment;
import com.mercadolibre.demo.model.PaymentStatus;
import com.mercadolibre.demo.service.PaymentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping(value = "/listAllPayments")
    @ResponseBody
    public ResponseEntity<List<Payment>> listAllPayments() {
        List<Payment> payments = paymentsService.listAllPayments();
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }

    @PutMapping(value = "/updatePaymentStatus/{id}")
    public ResponseEntity<Payment> updatePaymentStatus(@Valid @RequestBody PaymentDTO paymentDTO, @PathVariable Long id) throws Exception {
        Payment payment = paymentsService.updatePaymentStatus(paymentDTO, id);
        return new ResponseEntity<>(payment, HttpStatus.GONE);
    }
}