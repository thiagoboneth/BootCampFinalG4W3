package com.mercadolibre.demo.repository;

import com.mercadolibre.demo.model.SalesAd;
import com.mercadolibre.demo.model.SalesAdBuyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesAdBuyerRepository extends JpaRepository<SalesAdBuyer, Long> {

}
