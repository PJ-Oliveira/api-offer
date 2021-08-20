package com.pj.oferta.configuration.rabbit.rabbitlistener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pj.oferta.configuration.rabbit.cancelarofertadto.CancelarOfertaDTO;
import com.pj.oferta.configuration.rabbit.rabbitconfig.RabbitMQConfig;
import com.pj.oferta.repository.OfertaRepository;
import com.pj.oferta.service.OfertaService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.amqp.core.AmqpAdmin;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

@Component
public class RabbitMQListener {

    @Autowired
    private OfertaService ofertaService;

    @JsonIgnoreProperties()
    @RabbitListener(queues = RabbitMQConfig.QUEUE, containerFactory = "offerContainerFactory")
    public void listener(String message) {
        try {
            CancelarOfertaDTO cancelarOfertaDTO = new ObjectMapper().configure(FAIL_ON_UNKNOWN_PROPERTIES, false).readValue(message, CancelarOfertaDTO.class);
            //invoca o método cancelar
            ofertaService.cancelaOfertaById_Product(cancelarOfertaDTO);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
