package com.pj.offer.domain.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pj.offer.advice.exception.NotFoundException;
import com.pj.offer.config.rabbitmq.rabbitconfig.RabbitMQConfig;
import com.pj.offer.domain.Product;
import com.pj.offer.service.ProductService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

@Component
public class DeleteOfferDtoListener {

    @Autowired
    private ProductService productService;

    @RabbitListener(queues = RabbitMQConfig.QUEUE, containerFactory = "offerContainerFactory")
    public void listener(String message) {

        try {
            Product product = new ObjectMapper().configure(FAIL_ON_UNKNOWN_PROPERTIES, true).readValue(message, Product.class);

            productService.findProductByIdProduct(product.getIdProduct());
            if(product != null){
                productService.deleteProductByIdProduct(product.getIdProduct());
            }
        } catch (ListenerExecutionFailedException |
                JsonProcessingException |
                EmptyResultDataAccessException |
                NotFoundException
                exceptionsOfRabbitMqList)
        {
        exceptionsOfRabbitMqList.getStackTrace();
        }

    }

}
