
package com.elcom.management_library_common.rabbitmq.subpub;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;


public class ProducerSubPub {
    @Autowired
    private FanoutExchange exchange;
    
    @Autowired
    private RabbitTemplate template;
    
    public void senMsg(String message){
        template.convertAndSend(exchange.getName(), "", message);
        System.out.print("Send: " + message);
    }
}
