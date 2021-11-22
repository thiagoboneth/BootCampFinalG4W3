package com.mercadolibre.demo.controller;

import com.mercadolibre.demo.config.SecurityController;
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
public class ItemOfProductController implements SecurityController {

    @Autowired
    private ItemOfProductService itemOfProductService;

    @GetMapping(value = "/list")
    @ResponseBody
    public ResponseEntity<List<ItemOfProductDTO>> listItemOfProduct(Long name) {
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
    public ResponseEntity<ProductItenForCarsDTO> lisProductForCar(@PathVariable Long idCar) {
        ProductItenForCarsDTO itemOfProducts = itemOfProductService.cartItems(idCar);
        return new ResponseEntity<>(itemOfProducts, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> deletePurchaseOrder(@PathVariable Long id) throws Exception {
        try {
            itemOfProductService.resetCart(id);
            return new ResponseEntity<>("Produtos removidos do carrinho com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}