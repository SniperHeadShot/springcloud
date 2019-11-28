package com.bat.message.exchange.enums;

/**
 * 缓存键 枚举
 *
 * @author ZhengYu
 * @version 1.0 2019/11/28 15:53
 **/
public enum CacheKeyEnum {

    /**
     * 缓存键 过期时间(单位是秒)
     **/
    MEG_EXCHANGE_CACHE(0, "message:exchange:cache", 30, "消息中转服务缓存在Redis中的存在时间");

    private int index;
    private String key;
    private int ttlSeconds;
    private String desc;

    CacheKeyEnum(int index, String key, int ttlSeconds, String desc) {
        this.index = index;
        this.key = key;
        this.ttlSeconds = ttlSeconds;
        this.desc = desc;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getTtlSeconds() {
        return ttlSeconds;
    }

    public void setTtlSeconds(int ttlSeconds) {
        this.ttlSeconds = ttlSeconds;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
