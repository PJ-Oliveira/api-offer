package com.pj.oferta.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pj.oferta.enums.OfertaStatus;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Entity
@Table
public class Oferta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_oferta")
    private Long id;
    private Long id_Produto;
    //@NotBlank(message = "Informe a data ")
    @JsonFormat(pattern = "dd/MM/aaaa")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME,pattern = "dd/MM/aaaa" )
    private LocalDate inicio;
    @JsonFormat(pattern = "dd/MM/aaaa")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME,pattern = "dd/MM/aaaa" )
    private LocalDate fim;
    private String descricao;
    @Enumerated(EnumType.STRING)
    private OfertaStatus status;


    public Oferta() {
    }

    public Oferta(Long id_Produto, LocalDate inicio, LocalDate fim, String descricao, OfertaStatus status) {
        this.id_Produto = id_Produto;
        this.inicio = inicio;
        this.fim = fim;
        this.descricao = descricao;
        this.status = status;
    }

    public Long getId_Produto() {
        return id_Produto;
    }

    public void setId_Produto(Long id_Produto) {
        this.id_Produto = id_Produto;
    }

    public LocalDate getInicio() {
        return inicio;
    }

    public void setInicio(LocalDate inicio) {
        this.inicio = inicio;
    }

    public LocalDate getFim() {
        return fim;
    }

    public void setFim(LocalDate fim) {
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

    @Override
    public String toString() {
        return "Oferta{" +
                "id_Produto=" + id_Produto +
                ", inicio=" + inicio +
                ", fim=" + fim +
                ", descricao='" + descricao + '\'' +
                ", status=" + status +
                '}';
    }
}
