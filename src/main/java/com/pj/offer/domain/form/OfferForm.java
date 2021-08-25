package com.pj.offer.domain.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pj.offer.domain.Offer;
import com.pj.offer.enums.OfertaStatus;
import com.pj.offer.service.OfferService;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OfferForm implements  Serializable{

    private Long id;
    @NotNull(message = "O Id do produto é necessário")
    @Valid
    @OneToMany
    private List<Long> idProduct;
    @Valid
    @NotNull(message = "A data de início da oferta é necessária")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT-03")
    private Date inicio;
    @Valid
    @NotNull(message = "A data de fim da oferta é necessária")
    @Future
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT-03")
    private Date fim;
    @Valid
    @NotBlank(message = "A descrição da oferta é necessária")
    private String descricao;
    @Valid
    @NotNull(message = "O Status da oferta é necessário")
    private OfertaStatus status;
    @Column(precision = 5, scale = 4)
    @NotNull(message = "Informe um valor para o campo desconto")
    @Valid
    @Range(min=1, max=50, message = "O desconto deve ser de no mínimo 1% e no máximo 50%")
    private BigDecimal desconto;

}
