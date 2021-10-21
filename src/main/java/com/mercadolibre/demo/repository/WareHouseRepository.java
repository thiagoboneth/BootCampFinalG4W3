package com.mercadolibre.demo.repository;

import com.mercadolibre.demo.model.WareHouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WareHouseRepository extends JpaRepository<WareHouse, Long> {
}
