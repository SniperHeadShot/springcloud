package com.bat.commoncode.enums;

/**
 * 消息中转服务枚举
 *
 * @author ZhengYu
 * @version 1.0 2019/12/8 14:23
 **/
public enum MsgExchangeEnum {

    /**
     * 广播消息专用
     */
    MSG_COMMON_BROADCAST("cloud", "v1.exchange.common", "v1.routingKey.common.broadcast", "v1.queue.common.broadcast", 10000),

    MSG_DEAD_LETTER("dlx", "v1.exchange.dlx", "v1.routingKey.dlx", "v1.queue.dlx", 18000000);

    /**
     * 区分Rabbitmq连接个数
     */
    private String rabbitmqType;

    private String exchangeName;

    private String routingKey;

    private String queueName;

    /**
     * 默认的过期时间为30分钟
     */
    private Integer ttl;

    MsgExchangeEnum(String rabbitmqType, String exchangeName, String routingKey, String queueName, Integer ttl) {
        this.rabbitmqType = rabbitmqType;
        this.exchangeName = exchangeName;
        this.routingKey = routingKey;
        this.queueName = queueName;
        this.ttl = ttl;
    }

    public String getRabbitmqType() {
        return rabbitmqType;
    }

    public void setRabbitmqType(String rabbitmqType) {
        this.rabbitmqType = rabbitmqType;
    }

    public String getExchangeName() {
        return exchangeName;
    }

    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public Integer getTtl() {
        return ttl;
    }

    public void setTtl(Integer ttl) {
        this.ttl = ttl;
    }
}
