package com.mercadolibre.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolibre.demo.dto.InboundOrderDTO;
import com.mercadolibre.demo.dto.response.InboundOrderResponseDTO;
import com.mercadolibre.demo.model.InboundOrder;
import com.mercadolibre.demo.service.InboundOrderService;

@RestController
@RequestMapping("/api/v1/fresh-products/inboundorder/")
public class InboundOrderController {
	
	@Autowired
	private InboundOrderService inboundOrderService;

	@PostMapping(value ="/save")
	public ResponseEntity<InboundOrder> saveInboundOrder(@RequestBody InboundOrderDTO dto) throws Exception {
		try {
			InboundOrder inboundOrder = inboundOrderService.save(dto);
			return new ResponseEntity<>(inboundOrder, HttpStatus.CREATED);
		} catch (NoSuchFieldException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "/list")
	@ResponseBody
	public ResponseEntity<List<InboundOrder>> listInboundOrder() {
		List<InboundOrder> inboundOrders = inboundOrderService.list();
		return new ResponseEntity<>(inboundOrders, HttpStatus.OK);
	}
	
	@PutMapping(value = "/update")
	public ResponseEntity<InboundOrder> updateInboundOrder(@RequestBody InboundOrder inboundOrder) {
		InboundOrder i = inboundOrderService.update(inboundOrder);
		return new ResponseEntity<>(i, HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/delete")
	@ResponseBody
	public ResponseEntity<String> deleteInboundOrder(@RequestParam Long id) {
		inboundOrderService.delete(id);
		return new ResponseEntity<>("Product successfully deleted", HttpStatus.ACCEPTED);
	}
}