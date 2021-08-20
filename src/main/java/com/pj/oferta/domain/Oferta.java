package com.pj.oferta.domain;

import com.pj.oferta.enums.OfertaStatus;
import springfox.documentation.spring.web.json.Json;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table
public class Oferta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_oferta")
    private Long id;
    @Column(name = "id_produto")
    private Long Product;
    private Date inicio;
    private Date fim;
    private String descricao;
    @Enumerated(EnumType.STRING)
    private OfertaStatus status;
    @Column(precision = 5, scale = 4)
    private BigDecimal desconto;


    public Oferta() {
    }

    public Oferta(Long id, Long Product, Date inicio, Date fim, String descricao, OfertaStatus status, BigDecimal desconto) {
        this.id = id;
        this.Product = Product;
        this.inicio = inicio;
        this.fim = fim;
        this.descricao = descricao;
        this.status = status;
        this.desconto = desconto;
    }

    public Long getProduct() {
        return Product;
    }

    public void setProduct(Long product) {
        this.Product = product;
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
        return "Oferta{" +
                "id_Produto=" + Product +
                ", inicio=" + inicio +
                ", fim=" + fim +
                ", descricao='" + descricao + '\'' +
                ", status=" + status +
                ", desconto=" + desconto +
                '}';
    }
}
