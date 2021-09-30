package com.pj.offer.consumer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pj.offer.config.rabbitmq.RabbitMQConfig;
import com.pj.offer.domain.model.Product;
import com.pj.offer.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

@Component
@Slf4j
public class DeleteProductConsumer {

    @Autowired
    private ProductService productService;

    public static final String QUEUE = "Product";

    @JsonIgnoreProperties
    @RabbitListener(queues = QUEUE, containerFactory = "offerContainerFactory")
    public void listener(String message) {
        try {
            Product product = new ObjectMapper().configure(FAIL_ON_UNKNOWN_PROPERTIES, false).
                    readValue(message, Product.class);
            productService.deleteProductByIdProduct(product.getIdProduct());
            log.warn("If id " + product.getIdProduct() + "does not exist, it will catch a Exception");
        }catch (Exception exception) { exception.printStackTrace();
            log.error("Exception " + exception + " caught.");
        }
    }
}