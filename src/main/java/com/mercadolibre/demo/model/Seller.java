package com.mercadolibre.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "saller")
public class Saller {

	@Id
	private int id;
	@Column(name = "name")
	private String nome;
	@Column(name = "lastname")
	private String sobrenome;
}
