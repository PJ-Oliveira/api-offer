package com.pj.offer.domain.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.validator.constraints.Range;
import javax.persistence.Column;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OfferForm implements Serializable{

    @NotNull(message = "{field.products.not.null}")
    private List<ProductForm> products;
    @NotNull(message = "{field.inicio.not.null}")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone = "GMT-03")
    @FutureOrPresent
    private LocalDate inicio;
    @NotNull(message = "{field.fim.not.null}")
    @Future
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone = "GMT-03")
    private LocalDate fim;
    @NotBlank(message = "{field.descricao.not.blank}")
    private String descricao;
    @Column(precision = 5, scale = 4)
    @NotNull(message = "{field.desconto.not.null}")
    @Range(min=1, max=50, message = "{field.desconto.range}")
    @Positive
    private BigDecimal desconto;
    @NotNull(message = "{field.active.not.null}")
    private Boolean active;

}
