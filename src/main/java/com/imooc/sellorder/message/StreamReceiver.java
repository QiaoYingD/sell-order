package com.imooc.sellorder.message;

import com.imooc.sellorder.dto.CartDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(StreamClient.class)
@Slf4j
public class StreamReceiver {

    @StreamListener(StreamClient.OUT)
    public void process(String message) {
        log.info("StreamReceiver OUT: {}", message);
    }


    @StreamListener(StreamClient.OUT1)
    public void processCartDto(CartDto message) {
        log.info("StreamReceiver OUT1: {}", message);
    }

}
