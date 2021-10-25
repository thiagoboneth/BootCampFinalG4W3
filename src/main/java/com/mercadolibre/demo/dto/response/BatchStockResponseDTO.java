package com.mercadolibre.demo.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mercadolibre.demo.model.BatchStock;
import com.mercadolibre.demo.model.SalesAd;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class BatchStockResponseDTO {
	
	private Float currentTemperature;
	private Float minimumTemperature;
	private Long initialQuantity;
	private Long currentQuantity;
	@JsonIgnore
	private LocalDate manufacturingDate = LocalDate.now();
	@JsonIgnore
	private LocalDateTime manufacturingTime = LocalDateTime.now();
	@JsonIgnore
	private LocalDate dueDate = LocalDate.now();
	private SalesAd salesad;
	
	public static BatchStockResponseDTO convertDTO(BatchStock bachStock) {
		return new BatchStockResponseDTO(bachStock.getCurrentTemperature(), bachStock.getMinimumTemperature(),
				bachStock.getInitialQuantity(), bachStock.getCurrentQuantity(), bachStock.getManufacturingDate(),
				bachStock.getManufacturingTime(), bachStock.getDueDate(), bachStock.getSalesad());
	}
}
