package com.mercadolibre.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mercadolibre.demo.model.BatchStock;

import java.util.List;

@Repository
public interface BatchStockRepository extends JpaRepository<BatchStock, Long>{

    @Query(nativeQuery = true, value = "SELECT * From batch_stock bs where  bs.idsales_ad =?1 ORDER BY due_date DESC;")
    List<BatchStock> batchStockList(Long name);
}
