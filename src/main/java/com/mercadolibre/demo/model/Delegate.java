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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "section_code")
    private Section section_code;

    public Delegate(String name, String lastname, Section section_code) {
        this.name = name;
        this.lastname = lastname;
        this.section_code = section_code;
    }
}
