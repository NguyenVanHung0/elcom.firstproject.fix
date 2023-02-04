
package com.elcom.management_library_api.controller;

import com.elcom.management_library_common.rabbitmq.rpc.Client;
import com.elcom.management_library_common.rabbitmq.rpc.Server;
import com.elcom.management_library_common.rabbitmq.subpub.ProducerSubPub;
import com.elcom.management_library_common.rabbitmq.worker.ProducerWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rabbitmq")
public class RabbitmqController {
    
    @Autowired
    private ProducerWorker producerWorker;
    
    @Autowired
    private ProducerSubPub producerSubPub;
    
    @Autowired
    Server server;
    
    @GetMapping("worker")
    public ResponseEntity<?> sendMessageWorker(@RequestBody String message){
        producerWorker.sendMsg(message);
        return ResponseEntity.ok(message);
    }
    
    @GetMapping("subpub")
    public ResponseEntity<?> sendMessageSubPub(@RequestBody String message){
        producerSubPub.senMsg(message);
        return ResponseEntity.ok(message);
    }
    
    @GetMapping("rpc")
    public ResponseEntity<?> sendMessageRpc(@RequestBody String message){
        server.sendMes(message);
        return ResponseEntity.ok(message);
    }
}
