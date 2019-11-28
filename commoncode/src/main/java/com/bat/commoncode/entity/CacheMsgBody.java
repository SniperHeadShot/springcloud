package com.bat.commoncode.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * 缓存消息体
 *
 * @author ZhengYu
 * @version 1.0 2019/11/28 10:56
 **/
@Data
public class CacheMsgBody {

    private String cacheKey;

    private Object cacheValue;

    public CacheMsgBody() {
    }

    public CacheMsgBody(String cacheKey, Object cacheValue) {
        this.cacheKey = cacheKey;
        this.cacheValue = cacheValue;
    }

    @Override
    public String toString() {
        return String.format("cache message body, key=[%s] value=[%s]", cacheKey, JSONObject.toJSONString(cacheValue));
    }
}
