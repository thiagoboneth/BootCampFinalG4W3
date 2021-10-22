package com.mercadolibre.demo.model;

import lombok.*;

import java.io.Serializable;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Builder
@Entity
@Table(name = "ware_house")
public class WareHouse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ware_house")
    private Long id_ware_house;
    @Column(name = "ware_house_name")
    private String ware_house_name;

    public WareHouse(String name) {
        this.ware_house_name = name;
    }
}
