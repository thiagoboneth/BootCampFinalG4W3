package com.mercadolibre.demo.controller;

import com.mercadolibre.demo.dto.ItemOfProductDTO;
import com.mercadolibre.demo.dto.response.ProductInBathDTO;
import com.mercadolibre.demo.dto.response.ProductInBatchStockDTO;
import com.mercadolibre.demo.dto.response.ProductItenForCarsDTO;
import com.mercadolibre.demo.model.ItemOfProduct;
import com.mercadolibre.demo.service.ItemOfProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
    @RequestMapping("/api/v1/fresh-products/itemOfProduct")
public class ItemOfProductController {

    @Autowired
    private ItemOfProductService itemOfProductService;

    @PostMapping(value = "/save")
    public ResponseEntity<ItemOfProduct> saveWareHouse(@Valid @RequestBody ItemOfProductDTO dto) throws Exception {
        try {
            ItemOfProduct itemOfProduct = itemOfProductService.save(dto);
            return new ResponseEntity<>(itemOfProduct, HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/list")
    @ResponseBody
    public ResponseEntity<List<ItemOfProductDTO>> listBuyer(Long name) {
        List<ItemOfProductDTO> itemOfProducts = itemOfProductService.list(name);
        return new ResponseEntity<>(itemOfProducts, HttpStatus.OK);
    }

    @GetMapping(value = "/listProductOfBatchStock")
    @ResponseBody
    public ResponseEntity<List<ProductInBatchStockDTO>> listProductOfBatchStock() {
        List<ProductInBatchStockDTO> productInBatchStockDTO = itemOfProductService.listProductOfBatchStock();
        return new ResponseEntity<>(productInBatchStockDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/listOrderProduct/{name}")
    @ResponseBody
    public ResponseEntity<List<ProductInBathDTO>> listProduct(@PathVariable String name) {
        List<ProductInBathDTO> itemOfProducts = itemOfProductService.listOrderProduct(name);
        return new ResponseEntity<>(itemOfProducts, HttpStatus.OK);
    }

    @GetMapping(value = "/lisProductForCar/{idCar}")
    @ResponseBody
    public ResponseEntity<ProductItenForCarsDTO> listProduct(@PathVariable Long idCar) {
        ProductItenForCarsDTO itemOfProducts = itemOfProductService.intensDoCarrinho(idCar);
        return new ResponseEntity<>(itemOfProducts, HttpStatus.OK);
    }


    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> deleteWareHouse(@PathVariable Long id) throws Exception {
        try {
            itemOfProductService.delete(id);
            return new ResponseEntity<>("Carrinho deletados com sucesso", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}