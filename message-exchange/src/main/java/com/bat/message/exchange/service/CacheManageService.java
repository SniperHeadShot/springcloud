package com.bat.message.exchange.service;

import com.bat.commoncode.entity.CacheMsgBody;

/**
 * 缓存管理Service
 *
 * @author ZhengYu
 * @version 1.0 2019/11/28 10:33
 **/
public interface CacheManageService {

    /**
     * 将数据放进缓存中
     *
     * @param cacheMsgBody 缓存数据
     * @author ZhengYu
     */
    void putCache(CacheMsgBody cacheMsgBody);

    /**
     * 从缓存中获取数据
     *
     * @param key 缓存数据键
     * @return java.lang.Object
     * @author ZhengYu
     */
    Object getCache(String key);

    /**
     * 将数据放进缓存中
     *
     * @param cacheMsgBody 缓存数据
     * @author ZhengYu
     */
    void updateCache(CacheMsgBody cacheMsgBody);

    /**
     * 将数据从缓存中删除
     *
     * @param key 缓存数据
     * @author ZhengYu
     */
    void delCache(String key);
}
