package com.elcom.management_library_common.rabbitmq.worker;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigWorker {

    @Value("${queue.name.worker}")
    String queueName;

    @Bean("queueWorker")
    public Queue queue() {
        return new Queue(queueName);
    }

    @Bean
    public ProducerWorker producerWorker() {
        return new ProducerWorker();
    }

    @Bean
    public ConsumerWorker consumerWorker() {
        return new ConsumerWorker(1);
    }
}
