package com.mercadolibre.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "seller")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
