package com.bat.message.exchange.listener;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 公共广播 - 消费者
 *
 * @author ZhengYu
 * @version 1.0 2019/12/8 15:35
 **/
@Slf4j
@Component
public class BroadCastListener {

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(value = "v1.queue.common.broadcast", durable = "true", arguments = {
                            @Argument(name = "x-message-ttl", value = "5000", type = "java.lang.Integer"),
                            @Argument(name = "x-dead-letter-exchange", value = "v1.exchange.dlx"),
                            @Argument(name = "x-dead-letter-routing-key", value = "v1.routingKey.dlx")
                    }),
                    exchange = @Exchange(value = "v1.exchange.common", type = "topic"),
                    key = "v1.routingKey.common.broadcast"))
    public void msgListener(Message message, Channel channel) throws IOException {
        String msg = new String(message.getBody());
        log.info("接收到消息 ==> [{}]", msg);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
    }
}
