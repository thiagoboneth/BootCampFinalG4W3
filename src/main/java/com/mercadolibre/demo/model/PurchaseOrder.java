package com.mercadolibre.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "purchase_order")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PurchaseOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_purchase_order")
    private Long id;

    @Column(name = "date")
    private LocalDate date = LocalDate.now();

    @Column(name = "order_status")
    OrderStatus orderStatus = OrderStatus.CARRINHO;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_buyer")
    private Buyer idBuyer;

    @OneToMany(mappedBy = "purchaseOrder", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ItemOfProduct> itemOfProduct;

}
