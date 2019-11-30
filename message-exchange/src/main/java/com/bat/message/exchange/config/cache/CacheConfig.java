package com.bat.message.exchange.config.cache;

import com.bat.message.exchange.enums.CacheKeyEnum;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Cache 缓存配置
 *
 * @author ZhengYu
 * @version 1.0 2019/11/28 15:35
 **/
@Slf4j
@Configuration
public class CacheConfig {

    private static final int CACHE_KEY_TTL = 30 * 60;

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        return new RedisCacheManager(
                RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory),
                // 默认策略
                buildRedisCacheConfiguration(CACHE_KEY_TTL),
                // 指定 key 策略
                buildSpecifiedRedisCacheConfiguration()
        );
    }

    /**
     * 生成缓存配置
     *
     * @return org.springframework.data.redis.cache.RedisCacheConfiguration
     * @author ZhengYu
     */
    private RedisCacheConfiguration buildRedisCacheConfiguration(int ttl) {
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
        redisCacheConfiguration = redisCacheConfiguration.serializeValuesWith(
                RedisSerializationContext
                        .SerializationPair
                        .fromSerializer(jackson2JsonRedisSerializer)
        ).entryTtl(Duration.ofSeconds(ttl));
        return redisCacheConfiguration;
    }

    /**
     * 加载枚举中定义的缓存键过期时间进配置
     *
     * @return java.util.Map<java.lang.String, org.springframework.data.redis.cache.RedisCacheConfiguration>
     * @author ZhengYu
     */
    private Map<String, RedisCacheConfiguration> buildSpecifiedRedisCacheConfiguration() {
        Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>(CacheKeyEnum.values().length);
        Arrays.stream(CacheKeyEnum.values())
                .forEach(cacheKeyEnum ->
                        redisCacheConfigurationMap.put(cacheKeyEnum.getKey(), buildRedisCacheConfiguration(cacheKeyEnum.getTtlSeconds())));
        return redisCacheConfigurationMap;
    }

    /**
     * 主键生成器
     *
     * @return org.springframework.cache.interceptor.KeyGenerator
     * @author ZhengYu
     */
    @Bean
    public KeyGenerator customKeyGenerator() {
        // 使用 [类名:方法名] 作为缓存的键
        return (target, method, params) -> target.getClass().getName() + ":" + method.getName();
    }
}
