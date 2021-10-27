package com.mercadolibre.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

import javax.persistence.*;


@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "delegate")
public class Delegate implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_delegate")
    private Long idDelegate;
	
    @Column(name = "name")
    private String name;
    
    @Column(name = "lastname")
    private String lastname;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "section_code", nullable = false)
    private Section section_code;

	public Delegate(String name, String lastname, Section section_code) {
		this.name = name;
		this.lastname = lastname;
		this.section_code = section_code;
	}
}
