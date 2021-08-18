package com.pj.oferta.domain.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.pj.oferta.domain.Oferta;
import com.pj.oferta.enums.OfertaStatus;
import com.pj.oferta.repository.OfertaRepository;
import com.pj.oferta.service.OfertaService;
import jdk.jfr.Percentage;

import javax.persistence.Column;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class OfertaFORM implements Serializable {

    private Long id;
    @NotNull(message = "O Id do produto é necessário")
    private Long id_Produto;
    @NotNull(message = "A data de início da oferta é necessária")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT-03")
    private Date inicio;
    @NotNull(message = "A data de fim da oferta é necessária")
    @Future
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT-03")
    private Date fim;
    @NotBlank(message = "A descrição da oferta é necessária")
    private String descricao;
    @NotNull(message = "O Status da oferta é necessário")
    private OfertaStatus status;
    @Column(precision = 5, scale = 4)
    @NotNull(message = "O Desconto é necessário")
    private BigDecimal desconto;

    public Oferta converterFORM(OfertaService OS) {
        Oferta oferta = new Oferta(id, id_Produto, inicio, fim, descricao, status, desconto);
        OS.addOferta(oferta);
        return oferta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }
}
