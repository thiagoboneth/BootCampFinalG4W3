package com.mercadolibre.demo.service;

import com.mercadolibre.demo.dto.PaymentDTO;
import com.mercadolibre.demo.model.Payment;
import com.mercadolibre.demo.model.PaymentStatus;
import com.mercadolibre.demo.model.PurchaseOrder;
import com.mercadolibre.demo.repository.ItemOfProductRepository;
import com.mercadolibre.demo.repository.PaymentRepository;
import com.mercadolibre.demo.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class PaymentsService {

    private PaymentRepository paymentRepository;
    private PurchaseOrderRepository purchaseOrderRepository;
    private ItemOfProductRepository itemOfProductRepository;

    @Autowired
    public PaymentsService(PaymentRepository paymentRepository, PurchaseOrderRepository purchaseOrderRepository, ItemOfProductRepository itemOfProductRepository) {
        this.paymentRepository = paymentRepository;
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.itemOfProductRepository = itemOfProductRepository;
    }

    public Payment payment(Long idCart, PaymentStatus paymentStatus, Long installment) throws Exception {
        Optional<PurchaseOrder> existCart = findById(idCart);
        if (existCart.isPresent()){
            Long priceGet = paymentRepository.orderOfItem(idCart);
            PaymentDTO paymentDTO = new PaymentDTO();
            paymentDTO.setValueOfCart(priceGet);
            paymentDTO.setPaymentStatus(paymentStatus);
            paymentDTO.setInstallment(installment);
            Payment dto = convertPaymentoToDTO(paymentDTO);
            Payment payment = paymentRepository.save(dto);
            return payment;

        }else {
              throw new Exception("Carrinho inexistente");
        }
    }

    public Payment convertPaymentoToDTO(PaymentDTO payment){
        return new Payment(payment.getPaymentStatus(),payment.getValueOfCart(),payment.getInstallment());
    }

    public Optional<PurchaseOrder> findById(Long id) {
        return purchaseOrderRepository.findById(id);
    }


}
