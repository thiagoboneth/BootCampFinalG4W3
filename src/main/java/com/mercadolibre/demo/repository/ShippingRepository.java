package com.mercadolibre.demo.repository;

import com.mercadolibre.demo.model.Shipping;
import com.mercadolibre.demo.model.ShippingS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingRepository extends JpaRepository<Shipping, Long> {

}
