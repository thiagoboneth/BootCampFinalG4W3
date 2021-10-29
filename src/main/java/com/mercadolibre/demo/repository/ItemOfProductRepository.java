package com.mercadolibre.demo.repository;

import com.mercadolibre.demo.model.ItemOfProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemOfProductRepository extends JpaRepository<ItemOfProduct, List> {
}
