package com.mercadolibre.demo.model;


import java.io.Serializable;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "ware_house")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@NoArgsConstructor
public class WareHouse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ware_house")
    private Long idWareHouse;
	
    @Column(name = "ware_house_name", nullable = false)
    private String wareHouseName;

    public WareHouse(String name) {
        this.wareHouseName = name;
    }

    public WareHouse(Long idWareHouse, String wareHouseName) {
        this.idWareHouse = idWareHouse;
        this.wareHouseName = wareHouseName;
    }
}
