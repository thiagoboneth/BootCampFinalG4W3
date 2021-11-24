package com.mercadolibre.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Adress")
public class Adress {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_adress", nullable = false)
	private Long idAdress;

	@Column(name = "cep", nullable = false)
	private String cep;
	@Column(name = "logradouro", nullable = false)
	private String logradouro;
	@Column(name = "complemento", nullable = false)
	private String complemento;
	@Column(name = "referencia", nullable = false)
	private String referencia;
	@Column(name = "bairro", nullable = false)
	private String bairro;
	@Column(name = "localidade", nullable = false)
	private String localidade;
	@Column(name = "uf", nullable = false)
	private String uf;
	@Column(name = "fone1", nullable = false)
	private String fone1;
	@Column(name = "fone2", nullable = false)
	private String fone2;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_buyer")
	private Buyer idBuyer;

	public Adress(String cep, String logradouro,String referencia, String complemento, String bairro, String localidade, String uf, String fone1, String fone2, Buyer idBuyer) {
		this.cep = cep;
		this.logradouro = logradouro;
		this.referencia = referencia;
		this.complemento = complemento;
		this.bairro = bairro;
		this.localidade = localidade;
		this.uf = uf;
		this.fone1 = fone1;
		this.fone2 = fone2;
		this.idBuyer = idBuyer;
	}
}
