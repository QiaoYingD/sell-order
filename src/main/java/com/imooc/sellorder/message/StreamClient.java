package com.imooc.sellorder.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface StreamClient {

    String INPUT = "myMessageInput";

    String OUT = "myMessageOut";

    String INPUT1 = "myMessageInput1";

    String OUT1 = "myMessageOut1";

    @Input(INPUT)
    SubscribableChannel input();

    @Output(OUT)
    MessageChannel output();


    @Input(INPUT1)
    SubscribableChannel input1();

    @Output(OUT1)
    MessageChannel output1();

}
