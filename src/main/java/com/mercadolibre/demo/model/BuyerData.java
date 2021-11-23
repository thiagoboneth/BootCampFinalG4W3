package com.mercadolibre.demo.model;

import java.io.Serializable;

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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "buyer_data")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BuyerData implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idbuyer_data")
	private Long idBuyerData;
	
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "idbuyer", nullable = false)
	private Buyer buyer;
	
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
    
    @Column(name = "email", nullable = false)
    private String email;
    
    @Column(name = "telephone", nullable = false)
    private String telephone;
    
    @Column(name = "cellphone", nullable = false)
    private String cellphone;
    
    
	public BuyerData(Buyer buyer, String cep, String logradouro, String complemento, String referencia, String bairro,
			String localidade, String uf, String email, String telephone, String cellphone) {
		this.buyer = buyer;
		this.cep = cep;
		this.logradouro = logradouro;
		this.complemento = complemento;
		this.referencia = referencia;
		this.bairro = bairro;
		this.localidade = localidade;
		this.uf = uf;
		this.email = email;
		this.telephone = telephone;
		this.cellphone = cellphone;
	}


 
	
}
