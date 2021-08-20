package com.pj.offer.domain.dto;

import com.pj.offer.domain.Offer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class OfferDTO implements Serializable {

    private Long id;
    private Long id_Product;
    private Date inicio;
    private Date fim;
    private String descricao;
    private Enum status;
    private BigDecimal desconto;

    //TODO: Usar modelmapper

    public OfferDTO convertDTO(Offer offer){
        OfferDTO dto = new OfferDTO();
        dto.setId_Product(offer.getId_Product());
        dto.setInicio(offer.getInicio());
        dto.setFim(offer.getFim());
        dto.setDescricao(offer.getDescricao());
        dto.setStatus(offer.getStatus());
        dto.setDesconto(offer.getDesconto());
        dto.setId(offer.getId());
        return dto;
    }

    public List<OfferDTO> convertDTO(List<Offer> offer){
        return offer.stream().map(oferta1 -> convertDTO(oferta1)).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    public Enum getStatus() {
        return status;
    }

    public void setStatus(Enum status) {
        this.status = status;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
