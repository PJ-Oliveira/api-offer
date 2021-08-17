package com.pj.oferta.domain.dto;

import com.pj.oferta.domain.Oferta;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class OfertaDTO {

    private Long id;
    private Long id_Produto;
    private Date inicio;
    private Date fim;
    private String descricao;
    private Enum status;


    public OfertaDTO convertDTO(Oferta oferta){
        OfertaDTO dto = new OfertaDTO();
        dto.setId_Produto(oferta.getId_Produto());
        dto.setInicio(oferta.getInicio());
        dto.setFim(oferta.getFim());
        dto.setDescricao(oferta.getDescricao());
        dto.setStatus((oferta.getStatus()));
        return dto;
    }

    public List<OfertaDTO> convertDTO(List<Oferta> oferta){
        return oferta.stream().map(oferta1 -> convertDTO(oferta1)).collect(Collectors.toList());
    }

    public Enum getStatus() {
        return status;
    }

    public void setStatus(Enum status) {
        this.status = status;
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
