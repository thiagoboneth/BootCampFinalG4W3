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

import static com.mercadolibre.demo.model.PaymentStatus.EM_ANALISE;


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
        PaymentDTO paymentDTO = new PaymentDTO();
        if (existCart.isPresent()){
            switch (paymentStatus) {
                case PARCELADO:
                    paymentDTO = rentDTO(divDB(idCart,installment), paymentStatus, installment);
                    return paymentRepository.save(convertPaymentoToDTO(paymentDTO));
                case BOLETO:
                    paymentDTO = rentDTO(sumDB(idCart), paymentStatus, installment);
                    return paymentRepository.save(convertPaymentoToDTO(paymentDTO));
                case 3:
                    System.out.println("Wednesday");
                    break;
                case 4:
                    System.out.println("Thursday");
                    break;
                case 5:
                    System.out.println("Friday");
                    break;
                case 6:
                    System.out.println("Saturday");
                    break;
                case 7:
                    System.out.println("Sunday");
                    break;
//                return payment;
            }else if (paymentStatus.equals(EM_ANALISE)) {
                PaymentDTO paymentDTO = rentDTO(sumDB(idCart), paymentStatus, installment);
                Payment dto = convertPaymentoToDTO(paymentDTO);
                Payment payment = paymentRepository.save(dto);
                return payment;
            }

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
}
