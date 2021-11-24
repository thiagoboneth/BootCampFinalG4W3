package com.mercadolibre.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "discontBlackFriday")
public class DiscontBlackFriday {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDiscontBlackFriday")
    private Long idDiscontBlackFriday;

    @Column(name = "cupomName", nullable = false)
    private CuponsBlackFriday cuponsBlackFriday = CuponsBlackFriday.SEMCUPOM;

    @Column(name = "valueOfCart", nullable = false)
    private Double valueOfCart;

    @Column(name = "cupomValue", nullable = false)
    private String cupomValue;

    @Column(name = "valueWithDescont", nullable = false)
    private Double valueWithDescont;

    public DiscontBlackFriday(CuponsBlackFriday cuponsBlackFriday, Double valueOfCart, String cupomValue, Double valueWithDescont) {
        this.cuponsBlackFriday = cuponsBlackFriday;
        this.valueOfCart = valueOfCart;
        this.cupomValue = cupomValue;
        this.valueWithDescont = valueWithDescont;
    }
}
