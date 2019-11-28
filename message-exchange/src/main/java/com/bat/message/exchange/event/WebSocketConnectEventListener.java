package com.bat.message.exchange.event;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

/**
 * 使用监听器获取连接到webSocket的session
 *
 * @author ZhengYu
 * @version 1.0 2019/11/27 17:24
 **/
@Slf4j
@Component
public class WebSocketConnectEventListener {

    /**
     * websocket 连接建立事件 监听
     *
     * @param event 连接建立事件
     * @author ZhengYu
     */
    @EventListener
    public void handleWebSocketConnectedListener(SessionConnectEvent event) {
        log.info("=== [{}]", JSONObject.toJSONString(event));
    }

    /**
     * websocket 连接断开事件 监听
     *
     * @param event 连接断开事件
     * @author ZhengYu
     */
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        log.info("### [{}]", JSONObject.toJSONString(event));
    }
}
