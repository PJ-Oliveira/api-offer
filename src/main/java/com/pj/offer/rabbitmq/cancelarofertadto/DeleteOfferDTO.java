package com.pj.offer.rabbitmq.cancelarofertadto;

public class DeleteOfferDTO {

    private Long id_Product;

    public Long getId_Product() {
        return id_Product;
    }

    public void setId_Product(Long id_Product) {
        this.id_Product = id_Product;
    }

    public DeleteOfferDTO() {
    }

    public DeleteOfferDTO(Long idProduct) {
        id_Product = idProduct;
    }

    @Override
    public String toString() {
        return "CancelarOfertaDTO{" +
                "Product=" + id_Product +
                '}';
    }
}
