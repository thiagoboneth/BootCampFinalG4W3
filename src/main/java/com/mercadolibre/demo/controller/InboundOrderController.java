package com.mercadolibre.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolibre.demo.dto.InboundOrderDTO;
import com.mercadolibre.demo.dto.request.InboundOrdeRequestDTO;
import com.mercadolibre.demo.service.InboundOrderService;

@RestController
@RequestMapping("/api/v1/fresh-products/inboundorder/")
public class InboundOrderController {
	
	private InboundOrderService inboundOrderService;
	
	// CREATE
	@PostMapping()
	public ResponseEntity<InboundOrdeRequestDTO> createInboundOrder(@RequestBody InboundOrderDTO inboundOrderDTO) {
		return null;
		
	}
	
	// UPDATE
	@PutMapping
	public ResponseEntity<InboundOrdeRequestDTO> updateInboundOrder(@RequestBody InboundOrderDTO inboundOrderDTO) {
		return null;
		
	}
}