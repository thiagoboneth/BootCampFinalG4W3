package com.mercadolibre.demo.repository;

import com.mercadolibre.demo.model.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyerRepository extends JpaRepository<Buyer, Long> {
}
