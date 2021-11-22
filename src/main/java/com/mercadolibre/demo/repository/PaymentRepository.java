package com.mercadolibre.demo.repository;

import com.mercadolibre.demo.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query(nativeQuery = true ,value = "SELECT SUM(iop.quantity) FROM item_of_product iop WHERE iop.id_purchase_order =?1;")
    Long orderOfItem (Long name);

}
