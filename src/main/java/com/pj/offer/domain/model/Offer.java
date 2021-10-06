package com.pj.offer.domain.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Setter
@ToString
public class Offer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_Offer")
    private Long id;

    @OneToMany(cascade =  CascadeType.ALL)
    private List<Product> products;

    @Column(name = "Inicio")
    private LocalDate inicio;
    @Column(name = "Fim")
    private LocalDate fim;
    @Column(name = "Descricao")
    private String descricao;
    @Column(name = "Desconto")
    private BigDecimal desconto;
    private Boolean active;

    public Long getId() {
        return this.id;
    }

    public List<Product> getProducts() {
        return this.products;
    }

    public LocalDate getInicio() {
        return this.inicio;
    }

    public LocalDate getFim() {
        return this.fim;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public BigDecimal getDesconto() {
        return this.desconto;
    }

    public Boolean getActive() {
        return this.active;
    }
}
