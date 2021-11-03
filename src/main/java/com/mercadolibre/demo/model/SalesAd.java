package com.mercadolibre.demo.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.util.Optional;
import javax.persistence.*;


@Getter
@Setter
@Entity
@Table(name = "sales_ad")
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SalesAd implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idsales_ad", nullable = false)
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

    public SalesAd(Float volume, Float minimumTemperature, Float maximumTemperature, Double price, Optional<Seller> obtemSeller, Optional<Product> obtemProduct) {
        this.volume = volume;
        this.minimumTemperature = minimumTemperature;
        this.maximumTemperature = maximumTemperature;
        this.price = price;
        this.seller = obtemSeller.get();
        this.product = obtemProduct.get();

    }
}
