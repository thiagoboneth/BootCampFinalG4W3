package com.mercadolibre.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mercadolibre.demo.model.InboundOrder;

@Repository
public interface InboundOrderRepository extends JpaRepository<InboundOrder, Long> {

}
