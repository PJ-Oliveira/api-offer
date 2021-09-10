package com.pj.offer.domain.dto;
import lombok.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OfferDto implements Serializable {

    private Long id;
    private Long idProduct;
    private LocalDate inicio;
    private LocalDate fim;
    private String descricao;
    private BigDecimal desconto;
}
