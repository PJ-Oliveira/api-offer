package com.pj.oferta.domain.dto;

import com.pj.oferta.domain.Oferta;
import springfox.documentation.spring.web.json.Json;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class OfertaDTO implements Serializable {

    private Long id;
    private Long Product;
    private Date inicio;
    private Date fim;
    private String descricao;
    private Enum status;
    private BigDecimal desconto;

    //TODO: Usar modelmapper

    public OfertaDTO convertDTO(Oferta oferta){
        OfertaDTO dto = new OfertaDTO();
        dto.setProduct(oferta.getProduct());
        dto.setInicio(oferta.getInicio());
        dto.setFim(oferta.getFim());
        dto.setDescricao(oferta.getDescricao());
        dto.setStatus(oferta.getStatus());
        dto.setDesconto(oferta.getDesconto());
        dto.setId(oferta.getId());
        return dto;
    }

    public List<OfertaDTO> convertDTO(List<Oferta> oferta){
        return oferta.stream().map(oferta1 -> convertDTO(oferta1)).collect(Collectors.toList());
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
