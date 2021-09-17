package com.pj.offer.domain;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Offer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_Offer")
    private Long id;
    @Column(name = "id_Product")
    private Long idProduct;
    @Column(name = "Inicio")
    private LocalDate inicio;
    @Column(name = "Fim")
    private LocalDate fim;
    @Column(name = "Descricao")
    private String descricao;
    @Column(name = "Desconto")
    private BigDecimal desconto;
    private Boolean active;

}
