package com.mercadolibre.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "section")
public class Section implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Gera autoincrimento no banco de dados
    @Column(name = "section_code")
    private Long section_code;
    @Column(name = "capacity")
    private int capacity;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_ware_house")
    private WareHouse wareHouse;

    public Section(int capacity, WareHouse wareHouse) {
        this.capacity = capacity;
        this.wareHouse = wareHouse;
    }
}
