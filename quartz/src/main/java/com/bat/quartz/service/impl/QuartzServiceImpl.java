package com.bat.quartz.service.impl;

import com.bat.quartz.config.QuartzManager;
import com.bat.quartz.service.QuartzService;
import com.bat.quartz.task.TestJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    /**
     * 开启任务
     *
     * @param uniqueTaskKey 唯一任务标识
     * @return boolean
     * @author ZhengYu
     */
    @Override
    public boolean startTask(String uniqueTaskKey) {
        try {
            quartzManager.addJob(TestJob.class, uniqueTaskKey);
            return true;
        } catch (SchedulerException e) {
            log.info("开启定时任务出错 [{}]", e);
            return false;
        }
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
        try {
            quartzManager.removeJob(uniqueTaskKey);
            return true;
        } catch (SchedulerException e) {
            log.info("关闭定时任务出错 [{}]", e);
            return false;
        }
    }
}
