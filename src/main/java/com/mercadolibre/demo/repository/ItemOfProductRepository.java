package com.mercadolibre.demo.repository;

import com.mercadolibre.demo.dto.response.ProductInBathDTO;
import com.mercadolibre.demo.model.ItemOfProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemOfProductRepository extends JpaRepository<ItemOfProduct, Long> {

    @Query(nativeQuery = true, value = "SELECT s.* from  item_of_product s where upper(trim(s.id_purchase_order)) = ?1")
    List<ItemOfProduct> orderOfItem (Long name);

/*
    @Query(value = "DELETE FROM ItemOfProduct i WHERE i.purchaseOrder = ?1")
    void deleteItensCar (Long name);

    @Modifying
    @Query(value="DELETE FROM ItemOfProduct i WHERE i.purchaseOrder = ?1", nativeQuery = true)
    void deleteItensCar1(Long lastName);
*/


    // lista de produto
    @Query(nativeQuery = true, value = "SELECT s.* from  item_of_product s where upper(trim(s.id_purchase_order)) = ?1")
    List<ItemOfProduct> listProduct (Long name);


    // lista de produto
    @Query(nativeQuery = true, value = "SELECT p.name,bs.due_date, bs.current_quantity,  s.category, wh.ware_house_name, bs.idbatch_number from sales_ad sa, batch_stock bs, inbound_order io, section s ,products p, ware_house wh where sa.idsales_ad = bs.idsales_ad AND bs.idbatch_number = io.idbatch_number AND io.section_code = s.section_code AND p.idproduct = sa.idproduct AND s.id_ware_house = wh.id_ware_house")
    List<ProductInBathDTO> listOrderProductL (String name);

    @Query(nativeQuery = true, value = "SELECT s.* from  item_of_product s where upper(trim(s.id_purchase_order)) = ?1")
    List<ProductInBathDTO> listOrderProductC (String name);

    @Query(nativeQuery = true, value = "SELECT s.* from  item_of_product s where upper(trim(s.id_purchase_order)) = ?1")
    List<ProductInBathDTO> listOrderProductF (String name);




}
