package com.mercadolibre.demo.repository;

import com.mercadolibre.demo.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query(value = "SELECT SUM(iop.salesAd.price) FROM ItemOfProduct iop WHERE iop.purchaseOrder.id =?1")
    Double price (Long name);

    @Query(value = "SELECT SUM(iop.quantity) FROM ItemOfProduct iop WHERE iop.purchaseOrder.id =?1")
    Long quantity (Long name);
}
