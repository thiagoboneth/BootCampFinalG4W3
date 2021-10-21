package com.mercadolibre.demo.repository;

import com.mercadolibre.demo.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller,Long>{
}
