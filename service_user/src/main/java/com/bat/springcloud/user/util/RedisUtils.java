package com.bat.springcloud.user.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisUtils
 * @Description Redis 工具类
 * @Author ZhengYu
 * @Version: 1.0
 * @Create: 2019/5/30 11:15
 **/
@Component
public class RedisUtils {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * @Param [key, value, seconds]
     * @Return void
     * @Author ZhengYu
     * @Description: 将 String 值存入 Redis 并持有 seconds 秒
     * @Date 2019/5/30
     */
    public void setStringToRedis(String key, String value, long seconds) {
        ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
        stringStringValueOperations.set(key, value, seconds, TimeUnit.SECONDS);
    }

    /**
     * @Param [key]
     * @Return java.lang.String
     * @Author ZhengYu
     * @Description: 从 Redis 中 读取 String 值
     * @Date 2019/5/30
     */
    public String getStringFromRedis(String key) {
        ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
        return stringStringValueOperations.get(key);
    }
}
