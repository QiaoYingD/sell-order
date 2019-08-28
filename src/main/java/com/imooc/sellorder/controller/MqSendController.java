package com.imooc.sellorder.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class MqSendController {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @GetMapping("sendMyQueues")
    public void sendMyQueues() {
        amqpTemplate.convertAndSend("myQueues", "now" + new Date());
    }

    /**
     * 发送水果服务
     */
    @GetMapping("sendFruit")
    public void sendFruit() {
        amqpTemplate.convertAndSend("myOrder","fruitOrder", "now" + new Date());
    }

    /**
     * 发送数码服务
     */
    @GetMapping("sendComputer")
    public void sendComputer() {
        amqpTemplate.convertAndSend("myOrder","computerOrder", "now" + new Date());
    }

}
