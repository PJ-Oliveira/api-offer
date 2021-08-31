package com.pj.offer.domain;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
    private Date inicio;
    @Column(name = "Fim")
    private Date fim;
    @Column(name = "Descricao")
    private String descricao;
    @Column(name = "Desconto")
    private BigDecimal desconto;
}
