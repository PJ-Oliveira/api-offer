package com.pj.offer.domain.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pj.offer.domain.Offer;
import com.pj.offer.enums.OfertaStatus;
import com.pj.offer.service.OfferService;
import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OfferFORM implements  Serializable{

    private Long id;
    @NotNull(message = "O Id do produto é necessário")
    private Long id_Product;
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

}
