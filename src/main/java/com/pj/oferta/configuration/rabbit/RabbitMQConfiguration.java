package com.pj.oferta.configuration.rabbit;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RabbitMQConfiguration {

    private static final String NOME_EXCHANGE = "amq.direct";
    private AmqpAdmin amqpAdmin;

    public RabbitMQConfiguration(AmqpAdmin amqpAdmin){
        this.amqpAdmin = amqpAdmin;
    }

    private Queue fila(String nomeFila){
        return new Queue(nomeFila, true, false, false);
    }

    private DirectExchange trocaDireta(){
        return new DirectExchange(NOME_EXCHANGE);
    }

    private Binding relacionamento(Queue fila, DirectExchange troca){
        return new Binding(fila.getName(), Binding.DestinationType.QUEUE, troca.getName(), fila.getName(), null);
    }

    //Método
    @PostConstruct
    private void adiciona(){
        Queue filaOFerta = this.fila(RabbitMQConstantes.FILA_OFERTA);
        DirectExchange troca = this.trocaDireta();
        Binding ligacaoOferta = this.relacionamento(filaOFerta, troca);
        //criando as filas no RabbitMQ
        this.amqpAdmin.declareQueue(filaOFerta);

        this.amqpAdmin.declareExchange(troca);
        this.amqpAdmin.declareBinding(ligacaoOferta);

    }












}
