package com.mercadolibre.demo.repository;

import com.mercadolibre.demo.model.ItemOfProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesAdBuyerRepository extends JpaRepository<ItemOfProduct, Long> {

}
