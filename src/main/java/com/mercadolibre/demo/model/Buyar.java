package com.mercadolibre.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "buyar")
public class Buyar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Gera autoincrimento no banco de dados
    @Column(name = "idBuyar")
    private Long idBuyar;
    @Column(name = "name")
    private String name;
    @Column(name = "lastName")
    private String lastName;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Product> products;
}
