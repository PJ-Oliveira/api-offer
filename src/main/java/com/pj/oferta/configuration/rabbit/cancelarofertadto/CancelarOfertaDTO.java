package com.pj.oferta.configuration.rabbit.cancelarofertadto;

public class CancelarOfertaDTO {

    private Long Product;

    public Long getProduct() {
        return Product;
    }

    public void setProduct(Long product) {
        Product = product;
    }

    public CancelarOfertaDTO() {
    }

    public CancelarOfertaDTO(Long product) {
        Product = product;
    }

    @Override
    public String toString() {
        return "CancelarOfertaDTO{" +
                "Product=" + Product +
                '}';
    }
}
