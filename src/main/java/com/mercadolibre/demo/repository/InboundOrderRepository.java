package com.mercadolibre.demo.repository;

import com.mercadolibre.demo.dto.response.SectionNativeDTO;
import com.mercadolibre.demo.dto.response.StockByWareHouseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mercadolibre.demo.model.InboundOrder;

import java.util.List;

@Repository
public interface InboundOrderRepository extends JpaRepository<InboundOrder, Long> {

    @Query( value = "select new com.mercadolibre.demo.dto.response.SectionNativeDTO(p.name, bs.currentQuantity, w.wareHouseName) " +
            " from WareHouse w, Section sec, InboundOrder i, BatchStock bs, SalesAd s,  Product p " +
            " where s.product.id = p.id " +
            " and bs.idSalesAd.id = s.id " +
            " and i.batchStock.idBatchNumber = bs.idBatchNumber " +
            " and i.section.idSection = sec.idSection " +
            " and sec.WareHouse.idWareHouse = w.idWareHouse " +
            " and p.id = ?1 and w.idWareHouse = ?2")
    List<SectionNativeDTO> buscaCanseira(Long id, Long idWhareHouse); //id = produto

}
