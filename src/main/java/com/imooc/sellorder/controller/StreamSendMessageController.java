package com.imooc.sellorder.controller;

import com.imooc.sellorder.dto.CartDto;
import com.imooc.sellorder.message.StreamClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class StreamSendMessageController {


    @Autowired
    private StreamClient streamClient;


    /**
     * 通过stream注解发送mq消息
     */
    @GetMapping("/sendMessage")
    public void sendMessage() {
        String message = "now " + new Date();
        streamClient.output().send(MessageBuilder.withPayload(message).build());
    }


    /**
     * 通过stream注解发送mq消息
     */
//    @GetMapping("/sendMessage")
//    public void sendMessage() {
//        CartDto cartDto = new CartDto();
//        cartDto.setProductId("111");
//        streamClient.output().send(MessageBuilder.withPayload(cartDto).build());
//    }


}
