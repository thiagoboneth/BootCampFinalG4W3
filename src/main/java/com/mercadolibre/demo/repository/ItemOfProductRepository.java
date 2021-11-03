package com.mercadolibre.demo.repository;

import com.mercadolibre.demo.model.ItemOfProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemOfProductRepository extends JpaRepository<ItemOfProduct, List<Long>> {
}
