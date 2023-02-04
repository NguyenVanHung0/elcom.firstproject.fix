package com.elcom.management_library_common.rabbitmq.subpub;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

public class ConsumerSubPub {

    @RabbitListener(queues = "#{deleteQueue1.name}")
    public void receive1(String mes) throws InterruptedException {
        receiveMsg(mes, 1);
    }

    @RabbitListener(queues = "#{deleteQueue2.name}")
    public void receive2(String mes) throws InterruptedException {
        receiveMsg(mes, 2);
    }

    public void receiveMsg(String mes, int receiver) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        System.out.println("Received (" + receiver + "): " + mes);
        System.out.println("Consumer(" + receiver + ") Done in " + stopWatch.getTotalTimeSeconds() + "s");
    }
}
