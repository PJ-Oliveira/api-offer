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

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

public class OfertaFORM {

    @NotNull(message = "O Id do produto é necessário")
    private Long id_Produto;
    @NotNull(message = "A data de início da oferta é necessária")
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    //@JsonDeserialize(using = LocalDateDeserializer.class)
    //@JsonSerialize(using = LocalDateSerializer.class)
    private Date inicio;
    @NotNull(message = "A data de fim da oferta é necessária")
    @Future
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    //@JsonDeserialize(using = LocalDateDeserializer.class)
    //@JsonSerialize(using = LocalDateSerializer.class)
    private Date fim;
    @NotBlank(message = "A descrição da oferta é necessária")
    private String descricao;
    @NotNull(message = "O Status da oferta é necessário")
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
