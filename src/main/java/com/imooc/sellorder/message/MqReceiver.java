package com.imooc.sellorder.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 接收mq消息
 */
@Component
@Slf4j
public class MqReceiver {

    //1.@RabbitListener(queues = "myQueues")
    //2.自动创建队列@RabbitListener(queuesToDeclare = @Queue("myQueues"))
    //3.自动创建，exchange和Queues绑定
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("myQueues"),
            exchange = @Exchange("myExchange")
    ))
    public void process(String message) {
        log.info("MqReceiver {}", message);
    }

    /**
     *
     * 如：不同的服务下订单到订单服务时绑定区分订单是什么服务
     *
     */
    /**
     * 数码供应商服务，接收消息
     *
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("myOrder"),
            key = "computer",
            value = @Queue("computerOrder")

    ))
    public void processComputer(String message) {
        log.info("computer MqReceiver {}", message);
    }

    /**
     * 水果供应商服务，接收消息
     *
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("fruitOrder"),
            key = "fruit",
            exchange = @Exchange("myOrder")
    ))
    public void processFruit(String message) {
        log.info("fruit MqReceiver {}", message);
    }
}
