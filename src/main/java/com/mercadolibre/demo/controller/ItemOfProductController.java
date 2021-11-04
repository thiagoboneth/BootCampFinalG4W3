package com.mercadolibre.demo.controller;

import com.mercadolibre.demo.dto.ItemOfProductDTO;
import com.mercadolibre.demo.dto.WareHouseDTO;
import com.mercadolibre.demo.model.ItemOfProduct;
import com.mercadolibre.demo.model.WareHouse;
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


    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> deleteWareHouse(@PathVariable Long id) {
        try {
            itemOfProductService.delete(id);
            return new ResponseEntity<>("Itens de Produto deletados com sucesso", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}