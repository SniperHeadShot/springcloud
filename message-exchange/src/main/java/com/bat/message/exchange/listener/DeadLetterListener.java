package com.bat.message.exchange.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 死信队列 - 消费者
 *
 * @author ZhengYu
 * @version 1.0 2019/12/8 15:35
 **/
@Slf4j
@Component
@RabbitListener(
        containerFactory = "cloudRabbitListenerContainerFactory",
        bindings = @QueueBinding(
                value = @Queue(value = "v1.queue.dlx", durable = "true"),
                exchange = @Exchange(value = "v1.exchange.dlx"),
                key = "v1.routingKey.dlx"))
public class DeadLetterListener {

    @RabbitHandler
    public void msgListener(String msg) {
        log.info("死信队列接收到消息 ==> [{}]", msg);
    }
}
