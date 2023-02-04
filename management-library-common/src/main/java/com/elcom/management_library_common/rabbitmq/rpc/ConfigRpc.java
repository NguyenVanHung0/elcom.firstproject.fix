package com.elcom.management_library_common.rabbitmq.rpc;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigRpc {

    @Value("${queue.name.rpc}")
    private String queueName;

    @Value("${xchange.name.rpc}")
    private String directXchangeName;

    @Bean("queueRpc")
    public Queue queue() {
        return new Queue(queueName);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(directXchangeName);
    }
    
    @Bean
    public Binding binding(DirectExchange directExchange,@Qualifier("queueRpc") Queue queue){
        return BindingBuilder.bind(queue).to(directExchange).with("elcom");
    }
    
    @Bean
    public Client client(){
        return new Client();
    }
    
    @Bean
    public Server server(){
        return new Server();
    }
}
