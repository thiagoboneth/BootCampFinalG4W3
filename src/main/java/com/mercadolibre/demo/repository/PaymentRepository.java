package com.mercadolibre.demo.repository;

import com.mercadolibre.demo.dto.response.StockByWareHouseDTO;
import com.mercadolibre.demo.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query(value = " select new com.mercadolibre.demo.dto.response.StockByWareHouseDTO(sec.category, iop.quantity, s.price, w.wareHouseName, p.name )" +
            " from WareHouse w, Section sec, InboundOrder i, BatchStock bs, SalesAd s,  Product p, ItemOfProduct iop" +
            " where s.product.id = p.id " +
            " and bs.idSalesAd.id = s.id " +
            " and i.batchStock.idBatchNumber = bs.idBatchNumber " +
            " and i.section.idSection = sec.idSection " +
            " and sec.WareHouse.idWareHouse = w.idWareHouse" +
            " and iop.salesAd.id = s.id" +
            " and iop.purchaseOrder.id = ?1")
    List<StockByWareHouseDTO> pricesOfPurchase(Long id);
}
