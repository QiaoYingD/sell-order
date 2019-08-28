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

    @StreamListener("myMessageOut")
    public void process(String message) {
        log.info("StreamReceiver: {}", message);
    }


//    @StreamListener("myMessageOut")
//    public void process(CartDto message) {
//        log.info("StreamReceiver: {}", message);
//    }

}
