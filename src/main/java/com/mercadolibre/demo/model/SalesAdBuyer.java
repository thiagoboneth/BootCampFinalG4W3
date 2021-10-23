package com.mercadolibre.demo.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "sales_ad_buyers")

public class SalesAdBuyer implements Serializable {

	private static final long serialVersionUID = 1L;


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Gera autoincrimento no banco de dados
    @Column(name = "idseller_ad_buyers")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idseller_products")
    private SalesAd salesad;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_buyer")
    private Buyer buyer;

    public SalesAdBuyer(SalesAd salesad, Buyer buyer) {
        this.salesad = salesad;
        this.buyer = buyer;
    }
}
