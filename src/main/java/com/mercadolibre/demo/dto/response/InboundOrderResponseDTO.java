package com.mercadolibre.demo.dto.response;

import java.time.LocalDate;

import com.mercadolibre.demo.model.BatchStock;
import com.mercadolibre.demo.model.InboundOrder;
import com.mercadolibre.demo.model.Section;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class InboundOrderResponseDTO {
	
    private LocalDate orderDate = LocalDate.now();
    private BatchStock batchStock;
    private Section section;
    
    public static InboundOrderResponseDTO convertDTO(InboundOrder inboundOrder) {
    	return new InboundOrderResponseDTO(inboundOrder.getOrderDate(), inboundOrder.getBatchStock(), inboundOrder.getSection());
    }
}
