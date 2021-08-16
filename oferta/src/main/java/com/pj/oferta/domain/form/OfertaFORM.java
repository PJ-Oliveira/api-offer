package com.pj.oferta.domain.form;

import com.pj.oferta.domain.Oferta;
import com.pj.oferta.enums.OfertaStatus;
import com.pj.oferta.repository.OfertaRepository;
import com.pj.oferta.service.OfertaService;

import java.time.LocalDate;
import java.util.Date;

public class OfertaFORM {

    private Long id_Produto;
    private LocalDate inicio;
    private LocalDate fim;
    private String descricao;
    private OfertaStatus status;

    public Oferta converterFORM(OfertaService OS)
    {
        Oferta oferta = new Oferta(id_Produto, inicio, fim, descricao, status);
        OS.addOferta(oferta);
        return oferta;
    }

    public OfertaStatus getStatus() {
        return status;
    }

    public void setStatus(OfertaStatus status) {
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
