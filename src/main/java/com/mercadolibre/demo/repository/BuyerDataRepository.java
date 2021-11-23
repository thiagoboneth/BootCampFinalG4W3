package com.mercadolibre.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mercadolibre.demo.model.BuyerData;

@Repository
public interface BuyerDataRepository extends JpaRepository< BuyerData, Long>{

}
