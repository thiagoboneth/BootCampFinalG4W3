package com.mercadolibre.demo.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Setter
@Getter
@Entity
@Table(name = "buyer")
@NoArgsConstructor
@AllArgsConstructor
public class Buyer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Gera autoincrimento no banco de dados
    @Column(name = "idBuyer")
    private Long idBuyer;
    @Column(name = "name")
    private String name;
    @Column(name = "lastName")
    private String lastName;

    public Buyer(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }
}
