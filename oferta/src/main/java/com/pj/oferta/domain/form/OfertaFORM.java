package com.pj.oferta.domain.form;

import com.pj.oferta.domain.Oferta;
import com.pj.oferta.repository.OfertaRepository;
import com.pj.oferta.service.OfertaService;

import java.util.Date;

public class OfertaFORM {

    private Long id_Produto;
    private Date inicio;
    private Date fim;
    private String descricao;

    public Oferta converterFORM(OfertaService OS)
    {
        Oferta oferta = new Oferta(id_Produto, inicio, fim, descricao);
        OS.addOferta(oferta);
        return oferta;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
