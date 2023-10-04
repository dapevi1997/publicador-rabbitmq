package com.example.publicador;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public static final String EXCHANGE_TOPIC = "core-prueba-topic";
    public static final String EXCHANGE_FANOUT = "core-prueba-fanout";
    public static final String EXCHANGE_DIRECT = "core-prueba-direct";
    public static final String TOPIC_QUEUE_1 = "topic.queue1";
    public static final String TOPIC_QUEUE_2 = "topic.queue2";
    public static final String FANOUT_QUEUE_1 = "fanout.queue1";
    public static final String FANOUT_QUEUE_2 = "fanout.queue2";
    public static final String FANOUT_QUEUE_3 = "fanout.queue3";
    public static final String DIRECT_QUEUE = "direct.queue";
    public static final String ROUTING_KEY_DIRECT = "prueba.key";
    public static final String ROUTING_KEY_TOPIC = "topic.#";



    @Bean
    public RabbitAdmin rabbitAdmin(RabbitTemplate rabbitTemplate){
        var admin = new RabbitAdmin(rabbitTemplate);
        admin.declareExchange(new TopicExchange(EXCHANGE_TOPIC));
        admin.declareExchange(new DirectExchange(EXCHANGE_DIRECT));
        admin.declareExchange(new FanoutExchange(EXCHANGE_FANOUT));
        return admin;
    }
    @Bean
    public Queue directQueue(){
        return new Queue(DIRECT_QUEUE);
    }
    @Bean
    public Queue topicQueue1(){
        return new Queue(TOPIC_QUEUE_1);
    }
    @Bean
    public Queue topicQueue2(){
        return new Queue(TOPIC_QUEUE_2);
    }
    @Bean
    public Queue fanOutQueue1(){
        return new Queue(FANOUT_QUEUE_1);
    }
    @Bean
    public Queue fanOutQueue2(){
        return new Queue(FANOUT_QUEUE_2);
    }
    @Bean
    public Queue fanOutQueue3(){
        return new Queue(FANOUT_QUEUE_3);
    }

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(EXCHANGE_TOPIC);
    }
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(EXCHANGE_DIRECT);
    }
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange(EXCHANGE_FANOUT);
    }
    @Bean
    public Binding eventsBinding(){
        return BindingBuilder.bind(this.directQueue()).to(this.directExchange()).with(ROUTING_KEY_DIRECT);
    }
    @Bean
    public Binding bindingQueue1ToTopicExchange(){
        return BindingBuilder.bind(this.topicQueue1()).to(this.topicExchange()).with(ROUTING_KEY_TOPIC);
    }
    @Bean
    public Binding bindingQueue2ToTopicExchange(){
        return BindingBuilder.bind(this.topicQueue2()).to(this.topicExchange()).with(ROUTING_KEY_TOPIC);
    }

    @Bean
    public Binding fanoutToQueue1(){
        return BindingBuilder.bind(this.fanOutQueue1()).to(this.fanoutExchange());
    }
    @Bean
    public Binding fanoutToQueue2(){
        return BindingBuilder.bind(this.fanOutQueue2()).to(this.fanoutExchange());
    }
    @Bean
    public Binding fanoutToQueue3(){
        return BindingBuilder.bind(this.fanOutQueue3()).to(this.fanoutExchange());
    }
}
