package com.mercadolibre.demo.model;

import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "seller")
public class Seller {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Gera autoincrimento no banco de dados
	@Column(name = "idseller")
	private Long id;
	@Column(name = "name")
	private String name;
	@Column(name = "lastname")
	private String lastname;

	
}
