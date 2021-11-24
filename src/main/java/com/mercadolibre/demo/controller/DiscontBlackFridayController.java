package com.mercadolibre.demo.controller;

import com.mercadolibre.demo.config.SecurityController;
import com.mercadolibre.demo.dto.DiscontBlackFridayDTO;
import com.mercadolibre.demo.model.CuponsBlackFriday;
import com.mercadolibre.demo.model.DiscontBlackFriday;
import com.mercadolibre.demo.service.DiscontBlackFridayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/fresh-products/cupom-black-friday/")
public class DiscontBlackFridayController implements SecurityController {

    @Autowired
    DiscontBlackFridayService discontBlackFridayService;

    @PostMapping("/applyDiscount")
    @ResponseBody
    public ResponseEntity<DiscontBlackFridayDTO> registerDiscount(CuponsBlackFriday cupomBlackFriday, Long idCart){
        try {
            DiscontBlackFriday discontBlackFriday = discontBlackFridayService.registerDiscontBlackFriday(cupomBlackFriday,idCart);
            DiscontBlackFridayDTO discontBlackFridayDTO = discontBlackFridayService.convertDiscontBlackFridayDTO(discontBlackFriday);
            return new ResponseEntity<>(discontBlackFridayDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/list")
    @ResponseBody
    public ResponseEntity<List<DiscontBlackFridayDTO>> listDiscounts() {
        List<DiscontBlackFriday> discontsBlackFriday = discontBlackFridayService.listValueProductsWithdiscont();
        return new ResponseEntity(discontsBlackFriday, HttpStatus.OK);
    }

    @GetMapping(value = "/listEnums")
    @ResponseBody
    public ResponseEntity<List<DiscontBlackFridayDTO>> listEnums() {
        List<DiscontBlackFriday> discontsBlackFriday = discontBlackFridayService.listValueProductsWithdiscont();
        return new ResponseEntity(discontsBlackFriday, HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<DiscontBlackFriday> updateBuyer(@Valid @RequestBody DiscontBlackFridayDTO dto, @PathVariable Long id) throws Exception {
        DiscontBlackFriday discontBlackFriday = discontBlackFridayService.updateDiscontBlackFriday(dto, id);
        return new ResponseEntity<>(discontBlackFriday, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<DiscontBlackFriday> deleteCupom(@PathVariable Long id) throws Exception {
        try {
            discontBlackFridayService.deleteDiscontBlackFriday(id);
            return new ResponseEntity("Cupom " + id + " deletado com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
