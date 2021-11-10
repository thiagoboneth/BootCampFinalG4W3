package com.mercadolibre.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mercadolibre.demo.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	@Modifying
	@Query(value = "ALTER TABLE products AUTO_INCREMENT = 1", nativeQuery = true)
	void resetAutoIncrement();
}
