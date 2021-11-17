package com.mercadolibre.demo.repository;

import com.mercadolibre.demo.model.InboundOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InboundOrderRepository extends JpaRepository<InboundOrder, Long> {

	@Query(nativeQuery = true, value = "SELECT io.* from sales_ad sa," +
            " batch_stock bs, inbound_order io, section s ,products p," +
            " ware_house wh where sa.idsales_ad = bs.idsales_ad AND" +
            " bs.idbatch_number = io.idbatch_number AND io.section_code = s.section_code AND" +
            " p.idproduct = sa.idproduct AND s.id_ware_house = wh.id_ware_house AND p.idproduct= ?1 AND  wh.id_ware_house = ?2")
    List<InboundOrder> buscarSessaoInboundOrder(Long id, Long idWarehouse);

    @Query(nativeQuery = true, value = "select p.name, bs.current_quantity, w.ware_house_name " +
            " from ware_house w, section sec, inbound_order i, batch_stock bs, sales_ad s,  products p " +
            " where s.idproduct = p.idproduct " +
            " and bs.idsales_ad = s.idsales_ad " +
            " and i.idbatch_number = bs.idbatch_number " +
            " and i.section_code = sec.section_code " +
            " and sec.id_ware_house = w.id_ware_house " +
            " and p.idproduct = ?1")
    List<StockByWareHouse> buscaCanseira(Long id);

    public interface StockByWareHouse{
        String getName();
        Long getCurrent_quantity();
        String getWare_house_name();

    }
}
