package com.mercadolibre.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Getter
@Setter
@Entity
@Table(name = "section")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@NoArgsConstructor
public class Section implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "section_code")
    private Long sectionCode;
	
    @Column(name = "capacity")
    private Long capacity;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_ware_house", nullable = false)
    private WareHouse idWareHouse;

    public Section(Long capacity, WareHouse wareHouse) {
        this.capacity = capacity;
        this.idWareHouse = wareHouse;
    }
}
