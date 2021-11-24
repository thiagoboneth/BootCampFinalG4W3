package com.mercadolibre.demo.repository;

import com.mercadolibre.demo.dto.response.SectionNativeDTO;
import com.mercadolibre.demo.dto.response.StockByWareHouseDTO;
import com.mercadolibre.demo.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectionRepository extends JpaRepository<Section,Long> {

	//@Query(nativeQuery = true, value = "SELECT s.* from item_of_product iop, sales_ad sa, batch_stock bs, inbound_order io, section s ,products p, ware_house wh where iop.idsales_ad = sa.idsales_ad AND sa.idsales_ad = bs.idsales_ad AND bs.idbatch_number = io.idbatch_number AND io.section_code = s.section_code AND p.idproduct = sa.idproduct AND s.id_ware_house = wh.id_ware_house AND  upper(trim(s.category)) like %?1%")
	// @Query(value = "select sec.category, bs.currentQuantity, s.price, w.wareHouseName, p.name  from WareHouse w, Section sec, InboundOrder i, BatchStock bs, SalesAd s,  Product p where s.product.id = p.id and bs.idSalesAd.id = s.id and i.batchStock.idBatchNumber = bs.idBatchNumber and i.section.idSection = sec.idSection and sec.WareHouse.idWareHouse = w.idWareHouse and sec.category like %?1%")



//	@Query(nativeQuery = true, value = "select sec.category, bs.current_quantity, s.price, w.ware_house_name, p.name  " +
//			"from ware_house w, section sec, inbound_order i, batch_stock bs, sales_ad s,  products p " +
//			"where s.idproduct = p.idproduct " +
//			"and bs.idsales_ad = s.idsales_ad " +
//			"and i.idbatch_number = bs.idbatch_number " +
//			"and i.section_code = sec.section_code " +
//			"and sec.id_ware_house = w.id_ware_house" +
//			"and sec.category like %?1%")


	@Query(value = " select new com.mercadolibre.demo.dto.response.StockByWareHouseDTO(sec.category, bs.currentQuantity, s.price, w.wareHouseName, p.name )" +
			" from WareHouse w, Section sec, InboundOrder i, BatchStock bs, SalesAd s,  Product p " +
			" where s.product.id = p.id " +
			" and bs.idSalesAd.id = s.id " +
			" and i.batchStock.idBatchNumber = bs.idBatchNumber " +
			" and i.section.idSection = sec.idSection " +
			" and sec.WareHouse.idWareHouse = w.idWareHouse" +
			" and sec.category like %?1%")
	List<StockByWareHouseDTO> categoryContaining(String name);
}