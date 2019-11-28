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

    /**
     * Configure message broker options.
     *
     * @param registry 注册信息
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 广播式配置一个/topic消息代理
        registry.enableSimpleBroker("/topic");
        // 点对点配置一个/user消息代理
        registry.setUserDestinationPrefix("/user");
    }
}
