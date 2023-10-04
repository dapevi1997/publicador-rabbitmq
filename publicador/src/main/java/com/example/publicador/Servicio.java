package com.example.publicador;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class Servicio {
    public static final String EXCHANGE_TOPIC = "core-prueba-topic";
    public static final String EXCHANGE_DIRECT = "core-prueba-direct";
    public static final String EXCHANGE_FANOUT = "core-prueba-fanout";

    public static final String ROUTING_KEY_DIRECT = "prueba.key";
    public static final String ROUTING_KEY_TOPIC = "topic.routing.one";
    private final RabbitTemplate rabbitTemplate;



    public Servicio(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    public void publish(String message) {
        rabbitTemplate.convertAndSend(this.EXCHANGE_DIRECT,this.ROUTING_KEY_DIRECT,message.getBytes());
    }
    public void publishTopic(String message) {

        rabbitTemplate.convertAndSend(this.EXCHANGE_TOPIC,this.ROUTING_KEY_TOPIC,message.getBytes());
    }

    public void publishFonout(String message) {

        rabbitTemplate.convertAndSend(this.EXCHANGE_FANOUT,"",message.getBytes());
    }
}
