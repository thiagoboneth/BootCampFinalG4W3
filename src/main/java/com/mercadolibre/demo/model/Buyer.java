package com.mercadolibre.demo.model;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "buyer")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Buyer implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idBuyer")
    private Long idBuyer;
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "lastName", nullable = false)
    private String lastName;

    public Buyer(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }
}
