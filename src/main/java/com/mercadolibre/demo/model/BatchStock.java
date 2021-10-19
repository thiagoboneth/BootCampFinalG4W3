package com.mercadolibre.demo.model;

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
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "batch_stock")
public class BatchStock {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Gera autoincrimento no banco de dados
	@Column(name = "idbatch_number")
	private Long batchNumber;
	@Column(name = "current_temperature")
	private Float currentTemperature;
	@Column(name = "minimum_temperature")
	private Float minimumTemperature;
	@Column(name = "initial_quantity")
	private Long initialQuantity;
	@Column(name = "current_quantity")
	private Long currentQuantity;
	@Column(name = "manufacturing_date")
	private LocalDate manufacturingDate;
	@Column(name = "manufacturing_time")
	private LocalDateTime manufacturingTime;
	@Column(name = "due_date")
	private LocalDate dueDate;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "idseller_products")
	private SellerProducts sellerProducts;
}
