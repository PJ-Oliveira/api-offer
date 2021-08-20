package com.pj.offer.domain;

import com.pj.offer.enums.OfertaStatus;
import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table
public class Offer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_Offer")
    private Long id;
    @Column(name = "id_Product")
    private Long id_Product;
    @Column(name = "Início")
    private Date inicio;
    @Column(name = "Fim")
    private Date fim;
    @Column(name = "Descrição")
    private String descricao;
    @Enumerated(EnumType.STRING)
    @Column(name = "Status")
    private OfertaStatus status;
    @Column(name = "Desconto")
    private BigDecimal desconto;

    public Offer() {
    }

    public Offer(Long id, Long id_Product, Date inicio, Date fim, String descricao, OfertaStatus status, BigDecimal desconto) {
        this.id = id;
        this.id_Product = id_Product;
        this.inicio = inicio;
        this.fim = fim;
        this.descricao = descricao;
        this.status = status;
        this.desconto = desconto;
    }

    public Long getId_Product() {
        return id_Product;
    }

    public void setId_Product(Long id_Product) {
        this.id_Product = id_Product;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFim() {
        return fim;
    }

    public void setFim(Date fim) {
        this.fim = fim;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public OfertaStatus getStatus() {
        return status;
    }

    public void setStatus(OfertaStatus status) {
        this.status = status;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "id_Produto=" + id_Product +
                ", inicio=" + inicio +
                ", fim=" + fim +
                ", descricao='" + descricao + '\'' +
                ", status=" + status +
                ", desconto=" + desconto +
                '}';
    }
}
