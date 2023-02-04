package com.elcom.management_library_common.rabbitmq.worker;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

@RabbitListener(queues = "${queue.name.worker}")
public class ConsumerWorker {

    private final int srNo;

    public ConsumerWorker(int srNo) {
        this.srNo = srNo;
    }

    @RabbitHandler
    public void receiveMsg(String message) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        System.out.println("Received (" + srNo + "): " + message);
        stopWatch.stop();
        System.out.println("Consumer(" + srNo + ") Done in " + stopWatch.getTotalTimeSeconds() + "s");
    }

}
