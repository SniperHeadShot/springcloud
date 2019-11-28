package com.bat.message.exchange.config.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * 启动 WebSocket 支持
 *
 * @author ZhengYu
 * @version 1.0 2019/11/27 11:18
 **/
@Configuration
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }
}
