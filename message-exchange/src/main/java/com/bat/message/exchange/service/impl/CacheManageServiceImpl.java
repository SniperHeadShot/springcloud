package com.bat.message.exchange.service.impl;

import com.bat.commoncode.entity.CacheMsgBody;
import com.bat.message.exchange.dao.CacheManageDao;
import com.bat.message.exchange.service.CacheManageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 缓存管理Service
 *
 * @author ZhengYu
 * @version 1.0 2019/11/28 10:33
 **/
@Service
public class CacheManageServiceImpl implements CacheManageService {

    @Autowired
    private CacheManageDao cacheManageDao;

    /**
     * 将数据放进缓存中
     *
     * @param cacheMsgBody 缓存数据
     * @author ZhengYu
     */
    @Override
    public void putCache(CacheMsgBody cacheMsgBody) {
        // 参数校验
        if (cacheMsgBody == null || StringUtils.isEmpty(cacheMsgBody.getCacheKey()) || cacheMsgBody.getCacheValue() == null) {
            return;
        }
        cacheManageDao.putOrGetCache(cacheMsgBody.getCacheKey(), cacheMsgBody.getCacheValue());
    }


    /**
     * 从缓存中获取数据
     *
     * @param key 缓存数据键
     * @return java.lang.Object
     * @author ZhengYu
     */
    @Override
    public Object getCache(String key) {
        return cacheManageDao.putOrGetCache(key, null);
    }

    /**
     * 将数据放进缓存中
     *
     * @param cacheMsgBody 缓存数据
     * @author ZhengYu
     */
    @Override
    public void updateCache(CacheMsgBody cacheMsgBody) {
        Object updateCache = cacheManageDao.updateCache(cacheMsgBody.getCacheKey(), cacheMsgBody.getCacheValue());
    }

    /**
     * 将数据从缓存中删除
     *
     * @param key 缓存数据
     * @author ZhengYu
     */
    @Override
    public void delCache(String key) {
        cacheManageDao.delCache(key);
    }
}
