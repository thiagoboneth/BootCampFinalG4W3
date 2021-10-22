package com.mercadolibre.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mercadolibre.demo.model.SalesAd;

@Repository
public interface SalesAdRepository extends JpaRepository<SalesAd, Long> {

}
