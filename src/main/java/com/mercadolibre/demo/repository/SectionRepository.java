package com.mercadolibre.demo.repository;

import com.mercadolibre.demo.dto.SectionTypeDTO;
import com.mercadolibre.demo.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectionRepository extends JpaRepository<Section,Long> {

    //@Query(nativeQuery = true, value = "SELECT s.* from item_of_product iop, sales_ad sa, batch_stock bs, inbound_order io, section s ,products p, ware_house wh where iop.idsales_ad = sa.idsales_ad AND sa.idsales_ad = bs.idsales_ad AND bs.idbatch_number = io.idbatch_number AND io.section_code = s.section_code AND p.idproduct = sa.idproduct AND s.id_ware_house = wh.id_ware_house AND  upper(trim(s.category)) like %?1%")
    //@Query(value = "SELECT s.sectionCode, s.capacity, s.category, s.idWareHouse.idWareHouse from ItemOfProduct iop, SalesAd sa, BatchStock bs, InboundOrder io, Section s ,Product p, WareHouse wh where iop.salesAd.id = sa.id AND sa.id = bs.salesad.id AND bs.batchNumber = io.batchStock.batchNumber AND io.section.sectionCode = s.sectionCode AND p.id = sa.product.id AND s.idWareHouse.idWareHouse = wh.idWareHouse AND  upper(trim(s.category)) like %?1%")
    @Query(nativeQuery = true, value = "SELECT s.* from  section s where upper(trim(s.category)) like %?1%")
    List<Section> buscarPorSessao(String name);
}

//.category, iop.quantity, sa.price, wh.wareHouseName, p.name