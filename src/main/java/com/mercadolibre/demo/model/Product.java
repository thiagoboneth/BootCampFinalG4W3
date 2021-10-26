package com.mercadolibre.demo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "products")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idproduct")
	private Long id;
	
	@NotNull(message = "O nome não pode ser nulo")
	@NotEmpty (message = "O nome não pode estar vázio")
	@Column(name = "name", nullable = false)
	private String name;
	
	@NotNull(message = "A descricao não pode ser nulo")
	@NotEmpty (message = "A descricao não pode estar vázio")
	@Column(name = "description", nullable = false)
	private String description;
	
	
	public Product(String name, String description) {
		this.name = name;
		this.description = description;
	}
}
