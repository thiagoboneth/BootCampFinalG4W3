package com.mercadolibre.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.util.Optional;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "delegate")
@NoArgsConstructor
public class Delegate implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_delegate")
    private Long idDelegate;
	
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "lastname", nullable = false)
    private String lastname;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "section_code", nullable = false)
    private Section idSection;

	public Delegate(String name, String lastname, Optional<Section> idSection) {
		this.name = name;
		this.lastname = lastname;
		this.idSection = idSection.get();
	}
}
