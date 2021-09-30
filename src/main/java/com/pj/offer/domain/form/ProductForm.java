package com.pj.offer.domain.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductForm {

    @NotBlank(message = "{field.name.not.blank}")
    private String name;
    @NotBlank(message = "{field.type.not.blank}")
    private String type;
}
