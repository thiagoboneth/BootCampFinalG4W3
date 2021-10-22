package com.mercadolibre.demo.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.mercadolibre.demo.model.BatchStock;
import com.mercadolibre.demo.model.SalesAd;

import lombok.Getter;

@Getter
public class BatchStockDTO {

	@NotNull(message = "A temperatura atual não pode ser nula")
	@NotEmpty (message = "A temperatura atual não pode ser vazia")
	private Float currentTemperature;

	@NotNull(message = "A temperatura mínima atual não pode ser nula")
	@NotEmpty (message = "A temperatura mínima atual não pode estar vazia")
	private Float minimumTemperature;

	@NotNull(message = "A quantidade inicial não pode ser nula")
	@NotEmpty (message = "A quantidade inicial não pode estar vazia")
	private Long initialQuantity;

	@NotNull(message = "A quantidade atual não pode ser nula")
	@NotEmpty (message = "A quantidade atual não pode estar vázia")
	private Long currentQuantity;

	@NotNull(message = "A data de fabricacao não pode ser nula")
	@NotEmpty (message = "A data de fabricacao não pode estar vázia")
	private LocalDate manufacturingDate = LocalDate.now();

	@NotNull(message = "O tempo de fabricacao não pode ser nulo")
	@NotEmpty (message = "O tempo de fabricacao não pode estar vázio")
	private LocalDateTime manufacturingTime = LocalDateTime.now();

	@NotNull(message = "A data de vencimento não pode ser nula")
	@NotEmpty (message = "A data de vencimento não pode estar vázia")
	private LocalDate dueDate = LocalDate.now();

	@NotNull(message = "O anúncio de vendas não pode ser nulo")
	@NotEmpty (message = "O anúncio de vendas não pode estar vázio")
	private SalesAd salesad;


	public BatchStock convertObject() {
		return new BatchStock(currentTemperature, minimumTemperature, initialQuantity,currentQuantity,
				manufacturingDate, manufacturingTime, dueDate, salesad);
	}
}
