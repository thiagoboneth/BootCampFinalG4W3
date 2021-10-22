package com.mercadolibre.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "inbound_order")
public class InboundOrder implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderNumber", nullable = false)
    private Long id;
    @Column(name = "orderDate", nullable = false)
    private LocalDate orderDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idbatch_number", nullable = false)
    private BatchStock batchStock;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "section_code", nullable = false)
    private Section section;

	public InboundOrder(LocalDate orderDate, BatchStock batchStock, Section section) {
		this.orderDate = orderDate;
		this.batchStock = batchStock;
		this.section = section;
	}   
}
