package com.mercadolibre.demo.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "batch_stock")
public class BatchStock implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idbatch_number", nullable = false)
	private Long batchNumber;

	@Column(name = "current_temperature", nullable = false)
	private Float currentTemperature;

	@Column(name = "minimum_temperature", nullable = false)
	private Float minimumTemperature;

	@Column(name = "initial_quantity", nullable = false)
	private Long initialQuantity;

	@Column(name = "current_quantity", nullable = false)
	private Long currentQuantity;

	@Column(name = "manufacturing_date", nullable = false)
	private LocalDate manufacturingDate = LocalDate.now();

	@Column(name = "manufacturing_time", nullable = false)
	private LocalDateTime manufacturingTime = LocalDateTime.now();

	@Column(name = "due_date", nullable = false)
	private LocalDate dueDate = LocalDate.now();

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "idseller_products")
	private SalesAd salesad;

	public BatchStock(Float currentTemperature, Float minimumTemperature, Long initialQuantity, Long currentQuantity,
			LocalDate manufacturingDate, LocalDateTime manufacturingTime, LocalDate dueDate, SalesAd salesad) {
		this.currentTemperature = currentTemperature;
		this.minimumTemperature = minimumTemperature;
		this.initialQuantity = initialQuantity;
		this.currentQuantity = currentQuantity;
		this.manufacturingDate = manufacturingDate;
		this.manufacturingTime = manufacturingTime;
		this.dueDate = dueDate;
		this.salesad = salesad;
	}
}
