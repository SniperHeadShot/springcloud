package com.bat.message.exchange.config.socketjs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * Socket JS
 *
 * @author ZhengYu
 * @version 1.0 2019/11/27 16:24
 **/
@Slf4j
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketStompConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * Configure message broker options.
     *
     * @param registry 注册信息
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 表示指定一对一发送队列的前缀
        registry.setUserDestinationPrefix("/user");
        // 表示客户端订阅地址的前缀信息，也就是客户端接收服务端消息的地址的前缀信息
        registry.enableSimpleBroker("/topic");
    }

    /**
     * Register STOMP endpoints mapping each to a specific URL and (optionally)
     * enabling and configuring SockJS fallback options.
     *
     * @param registry 注册信息
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 设置端点，客户端通过http://[ip]:[port]/socketJsServer 来和服务器进行 websocket 连接
        registry.addEndpoint("/socketJsServer")
                .setAllowedOrigins("*")
                .withSockJS();
    }
}
