package com.mercadolibre.demo.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.mercadolibre.demo.model.BatchStock;
import com.mercadolibre.demo.model.InboundOrder;
import com.mercadolibre.demo.model.Section;

import lombok.Getter;

@Getter
public class InboundOrderDTO {
	
	@NotNull(message = "A data do pedido atual não pode ser nula")
	@NotEmpty (message = "A data do pedido atual não pode estar vazia")
    private LocalDate orderDate = LocalDate.now();
	
	@NotNull(message = "O lote do pedido atual não pode ser nulo")
	@NotEmpty(message = "O lote do pedido atual não pode estar vazio")
    private BatchStock batchStock;
	
	@NotNull(message = "A secao do pedido atual não pode ser nula")
	@NotEmpty (message = "A secao do pedido atual não pode estar vazia")
    private Section section;	
	
	
	public InboundOrder convertObject() {
		return new InboundOrder(orderDate, batchStock, section);
	}
}
