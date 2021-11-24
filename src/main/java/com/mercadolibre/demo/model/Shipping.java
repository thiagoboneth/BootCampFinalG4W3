package com.mercadolibre.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "shipping")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Shipping implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_shipping")
	private Long idsStatus;

	@Column(name = "code_track")
	private Long code_track;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_purchase_order", nullable = false)
	private PurchaseOrder id_purchase_order;

}
