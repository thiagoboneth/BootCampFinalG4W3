package com.mercadolibre.demo.model;


import javax.persistence.*;

import lombok.*;

import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "seller")
public class Seller implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idseller")
	private Long idseller;
	@Column(name = "name")
	private String name;
	@Column(name = "lastname")
	private String lastname;

	public Seller(String name, String lastname) {
		this.name = name;
		this.lastname = lastname;
	}
}
