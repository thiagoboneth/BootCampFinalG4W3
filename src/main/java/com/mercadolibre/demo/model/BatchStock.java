package com.mercadolibre.demo.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "batch_stock")
public class BatchStock implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idbatch_number", nullable = false)
	private Long idBatchNumber;

	@Column(name = "current_temperature", nullable = false)
	private Float currentTemperature;

	@Column(name = "minimum_temperature", nullable = false)
	private Float minimumTemperature;

	@Column(name = "initial_quantity", nullable = false)
	private Long initialQuantity;

	@Column(name = "current_quantity", nullable = false)
	private Long currentQuantity;

	@Column(name = "manufacturing_date", nullable = false)
	private LocalDate manufacturingDate;

	@Column(name = "manufacturing_time", nullable = false)
	private LocalDateTime manufacturingTime;

	@Column(name = "due_date", nullable = false)
	private LocalDate dueDate;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "idsales_ad")
	private SalesAd idSalesAd;

	public BatchStock(Float currentTemperature, Float minimumTemperature, Long initialQuantity, Long currentQuantity,
			LocalDate manufacturingDate, LocalDateTime manufacturingTime, LocalDate dueDate, Optional<SalesAd> idSalesAd) {
		this.currentTemperature = currentTemperature;
		this.minimumTemperature = minimumTemperature;
		this.initialQuantity = initialQuantity;
		this.currentQuantity = currentQuantity;
		this.manufacturingDate = manufacturingDate;
		this.manufacturingTime = manufacturingTime;
		this.dueDate = dueDate;
		this.idSalesAd = idSalesAd.get();
	}
}
