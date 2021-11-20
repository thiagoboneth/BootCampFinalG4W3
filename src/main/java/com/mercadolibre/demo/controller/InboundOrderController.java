package com.mercadolibre.demo.controller;

import java.util.List;

import com.mercadolibre.demo.config.SecurityController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mercadolibre.demo.dto.InboundOrderDTO;
import com.mercadolibre.demo.model.InboundOrder;
import com.mercadolibre.demo.service.InboundOrderService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/fresh-products/inboundorder/")
public class InboundOrderController implements SecurityController {
	
	@Autowired
	private InboundOrderService inboundOrderService;

	@PostMapping(value ="/save")
	public ResponseEntity<InboundOrder> saveInboundOrder(@Valid @RequestBody InboundOrderDTO dto) throws Exception {
			InboundOrder inboundOrder = inboundOrderService.save(dto);
			return new ResponseEntity<>(inboundOrder, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/list")
	@ResponseBody
	public ResponseEntity<List<InboundOrder>> listInboundOrder() {
		List<InboundOrder> inboundOrders = inboundOrderService.list();
		return new ResponseEntity<>(inboundOrders, HttpStatus.OK);
	}
	
	@PutMapping(value = "/update/{id}")
	public ResponseEntity<InboundOrder> updateInboundOrder(@Valid  @RequestBody InboundOrderDTO dto, @PathVariable Long id ) throws Exception {
			InboundOrder inboundOrder = inboundOrderService.update(dto,id);
			return new ResponseEntity<>(inboundOrder, HttpStatus.CREATED);
	}
}