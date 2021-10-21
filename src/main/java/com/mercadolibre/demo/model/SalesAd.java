package com.mercadolibre.demo.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "sales_ad")

public class SalesAd {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Gera autoincrimento no banco de dados
    @Column(name = "idseller_products")
    private Long id;
    @Column(name = "volume")
    private Float volume;
    @Column(name = "minimum_temperature")
    private Float minimumTemperature;
    @Column(name = "maximum_temperature")
    private Float maximumTemperature;
    @Column(name = "price")
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idseller")
    private Seller seller;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idproduct")
    private Product product;

}
