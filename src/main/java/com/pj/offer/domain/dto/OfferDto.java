package com.pj.offer.domain.dto;
import lombok.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OfferDto implements Serializable {

    private Long id;
    private Long idProduct;
    private Date inicio;
    private Date fim;
    private String descricao;
    private Enum status;
    private BigDecimal desconto;

}
