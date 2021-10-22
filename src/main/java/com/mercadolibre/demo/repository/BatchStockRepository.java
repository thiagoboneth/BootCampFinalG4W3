package com.mercadolibre.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mercadolibre.demo.model.BatchStock;

@Repository
public interface BatchStockRepository extends JpaRepository<BatchStock, Long>{
	
}
