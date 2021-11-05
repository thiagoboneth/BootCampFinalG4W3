package com.mercadolibre.demo.repository;

import com.mercadolibre.demo.dto.ItemOfProductDTO;
import com.mercadolibre.demo.model.ItemOfProduct;
import com.mercadolibre.demo.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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
    @Query(nativeQuery = true, value = "SELECT s.* from  item_of_product s ORDER BY quantity ASC")
    List<ItemOfProduct> listOrderProductL (String name);

    @Query(nativeQuery = true, value = "SELECT s.* from  item_of_product s where upper(trim(s.id_purchase_order)) = ?1")
    List<ItemOfProduct> listOrderProductC (String name);

    @Query(nativeQuery = true, value = "SELECT s.* from  item_of_product s where upper(trim(s.id_purchase_order)) = ?1")
    List<ItemOfProduct> listOrderProductF (String name);




}
