package com.mercadolibre.demo.controller;

import com.mercadolibre.demo.config.SecurityController;
import com.mercadolibre.demo.dto.ShippingSDTO;
import com.mercadolibre.demo.model.ShippingS;
import com.mercadolibre.demo.service.ShippingSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/fresh-products/shipping/status")
public class ShippingStatusController implements SecurityController {

	@Autowired
	private ShippingSService shippingSService;

	@PostMapping(value = "/save")
	public ResponseEntity<ShippingS> saveStatus(@Valid @RequestBody ShippingSDTO dto) {
		ShippingS shippingS = shippingSService.save(dto);
		return new ResponseEntity<>(shippingS, HttpStatus.CREATED);
	}

	@GetMapping(value = "/list")
	@ResponseBody
	public ResponseEntity<List<ShippingS>> listStatus() {
		List<ShippingS> shippingSList = shippingSService.list();
		return new ResponseEntity<>(shippingSList, HttpStatus.OK);
	}

	@PutMapping(value = "/update/{id}")
	public ResponseEntity<ShippingS> updateStatus(@Valid @RequestBody ShippingSDTO sellerDTO, @PathVariable Long id) throws Exception {
		ShippingS shippingS = shippingSService.update(sellerDTO, id);
		return new ResponseEntity<>(shippingS, HttpStatus.CREATED);
	}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<String> deleteStatus(@PathVariable Long id) throws Exception {
		shippingSService.delete(id);
		return new ResponseEntity<>("Status deletado com successfully", HttpStatus.OK);
	}
}
