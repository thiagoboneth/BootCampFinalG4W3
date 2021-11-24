package com.mercadolibre.demo.service;

import com.mercadolibre.demo.dto.PaymentDTO;
import com.mercadolibre.demo.dto.PaymentDTOBoleto;
import com.mercadolibre.demo.model.Payment;
import com.mercadolibre.demo.model.PaymentStatus;
import com.mercadolibre.demo.model.PurchaseOrder;
import com.mercadolibre.demo.repository.PaymentRepository;
import com.mercadolibre.demo.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentsService {

    private PaymentRepository paymentRepository;
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    public PaymentsService(PaymentRepository paymentRepository, PurchaseOrderRepository purchaseOrderRepository) {
        this.paymentRepository = paymentRepository;
        this.purchaseOrderRepository = purchaseOrderRepository;
    }

    public Payment payment(Long idCart, PaymentStatus paymentStatus, Long installment) throws Exception {
        Optional<PurchaseOrder> existCart = findById(idCart);
        if (existCart.isPresent()) {
            PaymentDTO paymentDTO;
            PaymentDTOBoleto paymentDTOBoleto;
            switch (paymentStatus) {
                case PARCELADO:
                    paymentDTO = rentDTO(divDB(idCart, installment), paymentStatus, installment);
                    return paymentRepository.save(convertPaymentoToDTO(paymentDTO));
                case BOLETO:
                    paymentDTOBoleto = rentDTOBoleto(sumDB(idCart),paymentStatus, installment, ramdomNumbers());
                    return paymentRepository.save(convertPaymentoToDTOBoleto(paymentDTOBoleto));
                case EM_ANALISE:
                    paymentDTO = rentDTO(sumDB(idCart), paymentStatus, installment);
                    return paymentRepository.save(convertPaymentoToDTO(paymentDTO));
                case CREDITO_A_VISTA:
                    paymentDTO = rentDTO(sumDB(idCart), paymentStatus, installment);
                    return paymentRepository.save(convertPaymentoToDTO(paymentDTO));
                case CANCELADO:
                    paymentDTO = rentDTO(sumDB(idCart), paymentStatus, installment);
                    return paymentRepository.save(convertPaymentoToDTO(paymentDTO));
                default:
                    throw new IllegalStateException("Error: " + paymentStatus);
            }
        }else {
                    throw new Exception("Carrinho inexistente");
        }
    }

    public Payment convertPaymentoToDTO(PaymentDTO payment){
        return new Payment(payment.getPaymentStatus(),payment.getValueOfCart(),payment.getInstallment());
    }

    public Payment convertPaymentoToDTOBoleto(PaymentDTOBoleto payment){
        return new Payment(payment.getPaymentStatus(),payment.getValueOfCart(),payment.getInstallment(),payment.getNumeroBoleto());
    }

    public String ramdomNumbers(){
        String validChar = "1234567890";
        String sequenceInit = "";
        char finalSequence;
        for (int k = 0;k<48;k++){
            finalSequence=validChar.charAt((int) Math.floor(Math.random()*validChar.length()));
            sequenceInit+=Character.toString(finalSequence);
        }
        return sequenceInit;
    }

    public Optional<PurchaseOrder> findById(Long id) {
        return purchaseOrderRepository.findById(id);
    }

    private Double sumDB(Long idCart){
        Long quantity = paymentRepository.quantity(idCart);
        Double price = paymentRepository.price(idCart);
        return quantity*price;
    }

    private Double divDB(Long priceOfProduct, Long installment){
        return sumDB(priceOfProduct)/installment;
    }

    private PaymentDTO rentDTO(Double sumDB, PaymentStatus paymentStatus, Long installment){
        return new PaymentDTO(paymentStatus,sumDB,installment);
    }

    private PaymentDTOBoleto rentDTOBoleto(Double sumDB, PaymentStatus paymentStatus,Long installment, String boletoNumero){
        return new PaymentDTOBoleto(paymentStatus,sumDB,installment,boletoNumero);
    }
}
