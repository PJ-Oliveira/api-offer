package com.pj.offer.domain;
import com.pj.offer.enums.OfertaStatus;
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
    private Long id_Product;
    @Column(name = "Inicio")
    private Date inicio;
    @Column(name = "Fim")
    private Date fim;
    @Column(name = "Descricao")
    private String descricao;
    @Enumerated(EnumType.STRING)
    @Column(name = "Status")
    private OfertaStatus status;
    @Column(name = "Desconto")
    private BigDecimal desconto;
}
