package com.bat.quartz.service.impl;

import com.bat.quartz.config.QuartzManager;
import com.bat.quartz.service.QuartzService;
import com.bat.quartz.task.CustomJob;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * QuartzServiceImpl
 *
 * @author ZhengYu
 * @version 1.0 2019/7/11 14:46
 **/
@Slf4j
@Service
public class QuartzServiceImpl implements QuartzService {

    @Autowired
    private QuartzManager quartzManager;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Value("${spring.cloud.client.ip-address}")
    private String serverHost;

    @Value("${server.port}")
    private String serverPort;

    /**
     * 开启任务
     *
     * @param uniqueTaskKey 唯一任务标识
     * @return boolean
     * @author ZhengYu
     */
    @Override
    public boolean startTask(String uniqueTaskKey) {
        log.info("正在尝试开启 [{}] 上的定时任务", concatIpPort(serverHost, serverPort));
        String redisKey = "task:server:" + uniqueTaskKey;
        // 校验任务是否已经存在
        boolean checkTaskExist = checkTaskIsExist(redisKey);
        if (!checkTaskExist) {
            try {
                quartzManager.addJob(CustomJob.class, uniqueTaskKey);
                stringRedisTemplate.opsForValue().set(redisKey, concatIpPort(serverHost, serverPort), 26, TimeUnit.HOURS);
                log.info("开启 [{}] 上的定时任务 成功", concatIpPort(serverHost, serverPort));
                return true;
            } catch (SchedulerException e) {
                log.info("开启定时任务出错 [{}]", e);
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 校验Redis是否存在定时任务
     *
     * @param redisKey 唯一key
     * @return boolean
     * @author ZhengYu
     */
    private boolean checkTaskIsExist(String redisKey) {
        String taskServerInfo = stringRedisTemplate.opsForValue().get(redisKey);
        log.info("定时任务 从Redis中根据 key=[{}] 获取到 任务详情=[{}]", redisKey, taskServerInfo);
        // 没有根据key获得value值则可以新建任务
        if (StringUtils.isEmpty(taskServerInfo)) {
            return false;
        }
        // 是否为本机开启的服务
        return !Objects.equals(concatIpPort(serverHost, serverPort), taskServerInfo);
    }

    /**
     * 停止任务
     *
     * @param uniqueTaskKey 唯一任务标识
     * @return boolean
     * @author ZhengYu
     */
    @Override
    public boolean stopTask(String uniqueTaskKey) {
        String redisKey = "task:server:" + uniqueTaskKey;
//        // 校验任务是否已经存在
//        boolean checkTaskExist = checkTaskIsExist(redisKey);
//
//        if(checkTaskExist){
//            return false;
//        }
        try {
            quartzManager.removeJob(uniqueTaskKey);
            return true;
        } catch (SchedulerException e) {
            log.info("关闭定时任务出错 [{}]", e);
            return false;
        }
    }
}
