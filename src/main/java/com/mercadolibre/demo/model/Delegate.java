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
@Table(name = "delegate")
public class Delegate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Gera autoincrimento no banco de dados
    @Column(name = "id_delegate")
    private Long idDelegate;
    @Column(name = "name")
    private String name;
    @Column(name = "lastname")
    private String lastname;

}
