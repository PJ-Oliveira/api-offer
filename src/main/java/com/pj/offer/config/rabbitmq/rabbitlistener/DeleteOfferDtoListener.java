package com.pj.offer.config.rabbitmq.rabbitlistener;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pj.offer.config.rabbitmq.cancelarofertadto.DeleteOfferDto;
import com.pj.offer.config.rabbitmq.rabbitconfig.RabbitMQConfig;
import com.pj.offer.service.OfferService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.validation.Valid;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

@Component
public class DeleteOfferDtoListener {

    @Autowired
    private OfferService offerService;

    @JsonIgnoreProperties()
    @RabbitListener(queues = RabbitMQConfig.QUEUE, containerFactory = "offerContainerFactory")
    public void listener(@Valid String message) {
        try {
            DeleteOfferDto deleteOfferDto = new ObjectMapper().configure(FAIL_ON_UNKNOWN_PROPERTIES, false).readValue(message, DeleteOfferDto.class);
            offerService.deleteOfferByDeleteOfferDTO(deleteOfferDto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
