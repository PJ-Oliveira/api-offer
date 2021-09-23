package com.pj.offer.domain.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pj.offer.advice.exception.NotFoundException;
import com.pj.offer.advice.exception.OfferException;
import com.pj.offer.config.rabbitmq.rabbitconfig.RabbitMQConfig;
import com.pj.offer.domain.Product;
import com.pj.offer.service.OfferService;
import com.pj.offer.service.ProductService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.validation.Valid;
import java.util.Optional;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

@Component
public class DeleteOfferDtoListener {

    @Autowired
    private OfferService offerService;
    @Autowired
    private ProductService productService;

    @JsonIgnoreProperties()
    @RabbitListener(queues = RabbitMQConfig.QUEUE, containerFactory = "offerContainerFactory")
    public void listener(@Valid String message) throws JsonProcessingException {

            Product product = new ObjectMapper().configure(FAIL_ON_UNKNOWN_PROPERTIES, false).readValue(message, Product.class);
            productService.findProductByIdProduct(product.getIdProduct());
            if(product != null){
                productService.deleteProductByIdProduct(product.getIdProduct());
            } else {
                //System.out.println("IdProduct is already deleted or else idProduct does not exist");
                throw new NotFoundException("IdProduct is already deleted or else idProduct does not exist");
            }
    }

}
