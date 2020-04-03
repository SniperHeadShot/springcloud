package com.bat.message.exchange.controller;

import com.alibaba.fastjson.JSONObject;
import com.bat.commoncode.enums.MsgExchangeEnum;
import com.bat.commoncode.util.UuidUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.IntStream;

/**
 * Rabbitmq 测试主类
 *
 * @author ZhengYu
 * @version 1.0 2020/4/1 9:17
 **/
@Slf4j
@RestController
@RequestMapping("/rabbitmq")
public class RabbitmqTestController {

    @Autowired
    private RabbitTemplate cloudRabbitTemplate;

    @GetMapping("/test")
    public String testRabbitMq() {
        IntStream.rangeClosed(1, 5).forEach(index -> {
            //cloudRabbitTemplate.convertAndSend(MsgExchangeEnum.MSG_COMMON_BROADCAST.getExchangeName(), MsgExchangeEnum.MSG_COMMON_BROADCAST.getRoutingKey(), String.format("消息[%d], id=[%s]", index, uuidStr), new CorrelationData(uuidStr));

            String uuidStr = UuidUtils.getRandomUuid();
            cloudRabbitTemplate.convertAndSend(
                    MsgExchangeEnum.MSG_COMMON_BROADCAST.getExchangeName(), MsgExchangeEnum.MSG_COMMON_BROADCAST.getRoutingKey(), String.format("消息[%d], id=[%s]", index, uuidStr), message -> {
                        MessageProperties messageProperties = message.getMessageProperties();
                        messageProperties.getHeaders().put("secretKey", uuidStr);
                        log.info("MessageProperties [{}]", JSONObject.toJSONString(messageProperties));
                        return message;
                    },
                    new CorrelationData(uuidStr));
            // cloudRabbitTemplate.convertAndSend(MsgExchangeEnum.MSG_COMMON_BROADCAST.getExchangeName(), MsgExchangeEnum.MSG_COMMON_BROADCAST.getRoutingKey(), String.format("Msg[%d]", index));
        });
        return "succ";
    }
}
