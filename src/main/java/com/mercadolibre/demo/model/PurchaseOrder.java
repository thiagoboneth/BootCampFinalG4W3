package com.mercadolibre.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "purchase_order")
public class PurchaseOrder implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Gera autoincrimento no banco de dados
    @Column(name = "id_purchase_order")
    private Long id;


    @Column(name = "date")
    private LocalDate date = LocalDate.now();

    @Column(name = "order_status")
    OrderStatus orderStatus = OrderStatus.CARRINHO;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_buyer")
    private Buyer buyer;

    @OneToMany(mappedBy = "purchaseOrder", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ItemOfProduct> itemOfProduct;

/*    public PurchaseOrder(LocalDate date, OrderStatus orderStatus, Buyer buyer, List<ItemOfProduct> itemOfProduct) {
        this.date = date;
        this.orderStatus = orderStatus;
        this.buyer = buyer;
        this.itemOfProduct = itemOfProduct;
    }

    public PurchaseOrder(Buyer buyer, List<ItemOfProduct> itemOfProduct) {
        this.buyer = buyer;
        this.itemOfProduct = itemOfProduct;
        this.date = getDate();
        this.orderStatus = OrderStatus.CARRINHO;
    }

    public PurchaseOrder(Buyer buyer) {
        this.buyer = buyer;
        this.date = getDate();
        this.orderStatus = OrderStatus.CARRINHO;
    }*/
}
