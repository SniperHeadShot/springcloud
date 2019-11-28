package com.bat.message.exchange.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.messaging.simp.SimpMessagingTemplate;

/**
 * 推送 STOMP 到指定 Topic
 *
 * @author ZhengYu
 * @version 1.0 2019/11/27 16:40
 **/
public class StompMsgSendUtil {

    /**
     * 推送 STOMP消息 到指定 Topic
     *
     * @param msg         消息
     * @param destination 目的地
     * @author ZhengYu
     */
    public static void sendSocketMsg(SimpMessagingTemplate messagingTemplate, String msg, String destination) {
        if (messagingTemplate == null || StringUtils.isEmpty(msg) || StringUtils.isEmpty(destination)) {
            // TODO 消息发送工具类 未完成
            return;
        }

    }
}
