package com.mercadolibre.demo.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Optional;
import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "item_of_product")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemOfProduct implements Serializable {

	private static final long serialVersionUID = 1L;


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Gera autoincrimento no banco de dados
    @Column(name = "idItem_of_product")
    private Long id;

    @Column(name = "quantity")
    private Long quantity;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idsales_ad")
    private SalesAd salesAd;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_purchase_order")
    private PurchaseOrder purchaseOrder;

    public ItemOfProduct(Long quantity, SalesAd salesAd, PurchaseOrder purchaseOrder) {
        this.quantity = quantity;
        this.salesAd = salesAd;
        this.purchaseOrder = purchaseOrder;
    }

    public ItemOfProduct(Long quantity, SalesAd salesAd) {
        this.quantity = quantity;
        this.salesAd = salesAd;
    }
}