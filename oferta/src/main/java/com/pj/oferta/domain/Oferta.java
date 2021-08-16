package com.pj.oferta.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table
public class Oferta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_oferta")
    private Long id;
    private Long id_Produto;
    private Date inicio;
    private Date fim;
    private String descricao;


    public Oferta() {
    }

    public Oferta(Long id_Produto, Date inicio, Date fim, String descricao) {
        this.id_Produto = id_Produto;
        this.inicio = inicio;
        this.fim = fim;
        this.descricao = descricao;
    }

    public Long getId_Produto() {
        return id_Produto;
    }

    public void setId_Produto(Long id_Produto) {
        this.id_Produto = id_Produto;
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

    @Override
    public String toString() {
        return "Oferta{" +
                "id_Produto=" + id_Produto +
                ", inicio='" + inicio + '\'' +
                ", fim='" + fim + '\'' +
                ", ofertaCode='" + id + '\'' +
                '}';
    }
}
