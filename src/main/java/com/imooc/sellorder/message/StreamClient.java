package com.imooc.sellorder.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface StreamClient {

    String INPUT = "myMessage";

    @Input("myMessageIn")
    SubscribableChannel input();

    @Output("myMessageOut")
    MessageChannel output();
}
