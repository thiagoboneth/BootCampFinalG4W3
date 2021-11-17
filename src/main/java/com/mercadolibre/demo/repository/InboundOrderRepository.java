package com.mercadolibre.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mercadolibre.demo.model.InboundOrder;

import java.util.List;

@Repository
public interface InboundOrderRepository extends JpaRepository<InboundOrder, Long> {

    @Query(nativeQuery = true, value = "select p.name, bs.current_quantity, w.ware_house_name " +
            " from ware_house w, section sec, inbound_order i, batch_stock bs, sales_ad s,  products p " +
            " where s.idproduct = p.idproduct " +
            " and bs.idsales_ad = s.idsales_ad " +
            " and i.idbatch_number = bs.idbatch_number " +
            " and i.section_code = sec.section_code " +
            " and sec.id_ware_house = w.id_ware_house " +
            " and p.idproduct = ?1 and w.id_ware_house = ?2")
    List<StockByWareHouse> buscaCanseira(Long id, Long idWhareHouse); //id = produto


    public interface StockByWareHouse{
        String getName();
        Long getCurrent_quantity();
        String getWare_house_name();
    }
}
