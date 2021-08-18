package com.pj.oferta.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQService {
    /*@Autowired
    private RabbitTemplate rabbitTemplate;
    public void enviaMensagem(String nomeFila, Object mensagem){
        this.rabbitTemplate.convertAndSend(nomeFila, mensagem);
    }*/

    @Autowired
    private RabbitTemplate rabbitTemplate;
    public void enviaMensagem(String nomeFila, Object mensagem){
        try {
            this.rabbitTemplate.convertAndSend(nomeFila, new ObjectMapper().writeValueAsString(mensagem));
        } catch (JsonProcessingException jsonProcessingException) {
            jsonProcessingException.printStackTrace();
        }
        //new ObjectMapper().writeValueAsString(nomeFila)
    }







}
