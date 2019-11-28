package com.bat.message.exchange.dao;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

/**
 * 缓存操作
 *
 * @author ZhengYu
 * @version 1.0 2019/11/28 14:23
 **/
@Slf4j
@CacheConfig(cacheNames = "message:exchange:cache")
@Repository
public class CacheManageDao {

    /**
     * 将数据放进缓存中
     *
     * @param cacheKey   缓存数据key
     * @param cacheValue 缓存数据value
     * @return java.lang.Object
     * @author ZhengYu
     */
    @Cacheable(key = "#root.args[0]", unless = "#result == null")
    public Object putOrGetCache(String cacheKey, Object cacheValue) {
        // @Cacheable(unless = "#result == null", keyGenerator = "customKeyGenerator") 指定缓存数据的键生成器

        log.info("成功将数据 key=[{}], value=[{}] 放入缓存", cacheKey, JSONObject.toJSONString(cacheValue));
        return cacheValue;
    }

    /**
     * 将缓存中的数据更新
     *
     * @param cacheKey      缓存数据key
     * @param newCacheValue 新的缓存数据value
     * @return java.lang.Object
     * @author ZhengYu
     */
    @CachePut(key = "#root.args[0]")
    public Object updateCache(String cacheKey, Object newCacheValue) {
        log.info("成功更新缓存数据 key=[{}], value=[{}]", cacheKey, JSONObject.toJSONString(newCacheValue));
        return newCacheValue;
    }

    @CacheEvict(key = "#root.args[0]")
    public void delCache(String cacheKey) {
        log.info("成功删除缓存数据 key=[{}]", cacheKey);
    }
}
