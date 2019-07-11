package com.bat.quartz.service;

/**
 * QuartzService
 *
 * @author ZhengYu
 * @version 1.0 2019/7/11 14:45
 **/
public interface QuartzService {

    /**
     * 开启任务
     *
     * @param uniqueTaskKey 唯一任务标识
     * @return boolean
     * @author ZhengYu
     */
    boolean startTask(String uniqueTaskKey);

    /**
     * 停止任务
     *
     * @param uniqueTaskKey 唯一任务标识
     * @return boolean
     * @author ZhengYu
     */
    boolean stopTask(String uniqueTaskKey);
}
