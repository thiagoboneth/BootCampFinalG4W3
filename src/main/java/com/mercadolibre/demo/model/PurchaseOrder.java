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
    private LocalDate date;

    @Column(name = "order_status")
    OrderStatus orderStatus = OrderStatus.CARRINHO;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_buyer")
    private Buyer buyer;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_purchase_order")
    private List<ItemOfProduct> itemOfProduct;
}
