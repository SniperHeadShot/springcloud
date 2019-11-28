package com.bat.message.exchange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 消息处理交换服务
 *
 * @author ZhengYu
 * @version 1.0 2019/10/30 11:01
 **/
@EnableCaching
@SpringBootApplication
public class MessageExchangeApplication {
    public static void main(String[] args) {
        SpringApplication.run(MessageExchangeApplication.class, args);
    }
}
