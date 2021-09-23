package com.pj.offer.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;


@Getter
@Setter
@ToString
@Table(name = "tb_product")
@Entity
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Product")
    private Long idProduct;
    @Column(name = "name_Product")
    private String name;
    @Column(name = "type_Product")
    private String type;

    //o problema é que ele ta recebendo mais que o id lá
    //verificando como resolver esse problema com o RABBITMQ
    public Product(Long idProduct) {
        this.idProduct = idProduct;
    }

    public Product() {
    }
}
