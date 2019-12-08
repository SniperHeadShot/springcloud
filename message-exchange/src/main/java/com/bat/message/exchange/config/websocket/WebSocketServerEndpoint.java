package com.bat.message.exchange.config.websocket;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Websocket 服务端
 * <p>
 * {@link #sendBroadcastInfo(String, String) 服务器向客户端发送消息}
 *
 * @author ZhengYu
 * @version 1.0 2019/11/27 11:23
 **/
@Slf4j
@Data
@ServerEndpoint("/websocket/{uniqueKey}")
@Component
public class WebSocketServerEndpoint {

    /**
     * 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的
     */
    private static final AtomicLong ONLINE_COUNT = new AtomicLong();

    /**
     * concurrent包的线程安全Set，用来存放每个客户端对应的WebSocketServer对象
     */
    private static CopyOnWriteArraySet<WebSocketServerEndpoint> webSocketSet = new CopyOnWriteArraySet<>();

    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;

    private String uniqueKey;

    /**
     * 连接建立成功调用的方法
     *
     * @param session   会话
     * @param uniqueKey 标识
     * @author ZhengYu
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("uniqueKey") String uniqueKey) {
        this.session = session;
        // 加入set中
        webSocketSet.add(this);
        // 在线人数+1
        ONLINE_COUNT.incrementAndGet();
        log.info("有新的客户端 [{}], 当前在线人数为: [{}]", uniqueKey, ONLINE_COUNT.get());

        this.uniqueKey = uniqueKey;

        try {
            sendMsg("连接成功");
        } catch (IOException e) {
            log.error("WebSocket Exception ==>[{}] [{}]", e.getMessage(), e);
        }
    }

    /**
     * 连接关闭调用的方法
     *
     * @author ZhengYu
     */
    @OnClose
    public void onClose() {
        // 从set中移除
        webSocketSet.remove(this);
        // 在线人数-1
        ONLINE_COUNT.decrementAndGet();
        log.info("客户端 [{}] 关闭, 当前在线人数为: [{}]", uniqueKey, ONLINE_COUNT.get());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param msg 消息体
     * @author ZhengYu
     */
    @OnMessage
    public void onMessage(String msg) {
        log.info("收到来自客户端 [{}] 的消息 [{}]", uniqueKey, msg);
        // do something
    }

    /**
     * 服务器向客户端发送消息
     *
     * @param msg       消息
     * @param uniqueKey 标识(不填则广播，填则指定客户端)
     * @author ZhengYu
     */
    public static void sendBroadcastInfo(String msg, String uniqueKey) {
        webSocketSet.stream()
                .filter(webSocketServerEndpoint -> {
                    if (StringUtils.isEmpty(uniqueKey)) {
                        return true;
                    }
                    return webSocketServerEndpoint != null && uniqueKey.equals(webSocketServerEndpoint.getUniqueKey());
                })
                .forEach(webSocketServerEndpoint -> {
                    try {
                        webSocketServerEndpoint.sendMsg(msg);
                    } catch (IOException e) {
                        log.error("WebSocket Exception ==>[{}] [{}]", e.getMessage(), e);
                    }
                });
    }

    /**
     * 连接发生错误调用的方法
     *
     * @author ZhengYu
     */
    @OnError
    public void onError(Throwable error) {
        log.error("客户端 [{}] 发送错误 ==>[{}] [{}]", uniqueKey, error.getMessage(), error);
    }

    /**
     * 服务器主动推送消息
     *
     * @param msg 消息
     * @author ZhengYu
     */
    private void sendMsg(String msg) throws IOException {
        this.session.getBasicRemote().sendText(msg);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        WebSocketServerEndpoint that = (WebSocketServerEndpoint) o;

        return new EqualsBuilder()
                .append(session, that.session)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(session)
                .toHashCode();
    }
}
