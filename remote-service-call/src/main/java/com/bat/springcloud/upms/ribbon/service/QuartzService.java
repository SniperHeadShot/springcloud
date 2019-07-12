package com.bat.springcloud.upms.ribbon.service;

import com.bat.common.response.CommonResult;

/**
 * 定时任务调用接口
 *
 * @author ZhengYu
 * @version 1.0 2019/7/12 10:30
 **/
public interface QuartzService {
    /**
     * 开启任务
     *
     * @param uniqueTaskKey 唯一任务标识
     * @return boolean
     * @author ZhengYu
     */
    CommonResult startTask(String uniqueTaskKey);

    /**
     * 停止任务
     *
     * @param uniqueTaskKey 唯一任务标识
     * @return boolean
     * @author ZhengYu
     */
    CommonResult stopTask(String uniqueTaskKey);
}
