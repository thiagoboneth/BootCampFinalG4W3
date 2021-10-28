package com.mercadolibre.demo.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "sales_ad")
public class SalesAd implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idseller_products", nullable = false)
    private Long id;
	
    @Column(name = "volume", nullable = false)
    private Float volume;
    
    @Column(name = "minimum_temperature", nullable = false)
    private Float minimumTemperature;
    
    @Column(name = "maximum_temperature", nullable = false)
    private Float maximumTemperature;
    
    @Column(name = "price")
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idseller", nullable = false)
    private Seller seller;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idproduct", nullable = false)
    private Product product;

	public SalesAd(Float volume, Float minimumTemperature, Float maximumTemperature, Double price, Seller seller,
			Product product) {
		this.volume = volume;
		this.minimumTemperature = minimumTemperature;
		this.maximumTemperature = maximumTemperature;
		this.price = price;
		this.seller = seller;
		this.product = product;
	}


}
