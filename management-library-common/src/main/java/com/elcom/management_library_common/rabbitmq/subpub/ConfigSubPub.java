package com.elcom.management_library_common.rabbitmq.subpub;

import org.springframework.amqp.core.AnonymousQueue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigSubPub {

    @Value("${fanout.name.Subpub}")
    private String fanoutName;

    @Bean
    public FanoutExchange exchange() {
        return new FanoutExchange(fanoutName);
    }

    @Bean
    public Queue deleteQueue1() {
        return new AnonymousQueue();
    }

    @Bean
    public Queue deleteQueue2() {
        return new AnonymousQueue();
    }

    @Bean
    public Binding binding1(FanoutExchange fanout, Queue deleteQueue1) {
        return BindingBuilder.bind(deleteQueue1).to(fanout);
    }

    @Bean
    public Binding binding2(FanoutExchange fanout, Queue deleteQueue2) {
        return BindingBuilder.bind(deleteQueue2).to(fanout);
    }
    
    @Bean
    public ProducerSubPub producerSubPub(){
        return new ProducerSubPub();
    }
    
    @Bean 
    public ConsumerSubPub consumerSubPub(){
        return new ConsumerSubPub();
    }

}
