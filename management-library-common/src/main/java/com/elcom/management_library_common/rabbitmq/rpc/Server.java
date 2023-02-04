
package com.elcom.management_library_common.rabbitmq.rpc;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;


public class Server {
//    @RabbitListener(queues = "${queue.name.rpc}")
//    public void receiveMsg(String mes){
//        System.out.println("Message nhan duoc la: " + mes);
//    }
    @Autowired
    RabbitTemplate rabbitTemplate;
    
    @Autowired
    DirectExchange directExchange;
    
    public void sendMes(String mes){
        System.out.println("Message gui di: " + mes);
        rabbitTemplate.convertSendAndReceive(directExchange.getName(), "elcom", mes);
    }
}
