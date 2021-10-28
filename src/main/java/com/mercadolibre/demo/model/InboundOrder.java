package com.mercadolibre.demo.model;

import java.io.Serializable;
import java.time.LocalDate;
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
@Setter
@Getter
@Entity
@Table(name = "inbound_order")
public class InboundOrder implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idInbound_order", nullable = false)
    private Long id;
    @Column(name = "order_date", nullable = false)
    private LocalDate orderDate = LocalDate.now();

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
