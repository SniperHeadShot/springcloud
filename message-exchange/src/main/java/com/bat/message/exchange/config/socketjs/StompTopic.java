package com.bat.message.exchange.config.socketjs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

/**
 * Stomp 订阅 Topic
 *
 * @author ZhengYu
 * @version 1.0 2019/11/27 16:36
 **/
@Slf4j
@Controller
public class StompTopic {

    /**
     * 订阅测试
     *
     * @return java.lang.String
     * @author ZhengYu
     */
    @SubscribeMapping("/topic/subscribeTest")
    public String subScribe() {
        return "subscribe success!";
    }
}
