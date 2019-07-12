package com.bat.springcloud.upms.ribbon.service.impl;

import com.bat.common.enums.ConstantEnum;
import com.bat.common.response.CommonResult;
import com.bat.springcloud.upms.ribbon.service.QuartzService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 定时任务调用实现
 *
 * @author ZhengYu
 * @version 1.0 2019/7/12 10:30
 **/
@Service
public class QuartzServiceImpl implements QuartzService {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 开启任务
     *
     * @param uniqueTaskKey 唯一任务标识
     * @return boolean
     * @author ZhengYu
     */
    @Override
    @HystrixCommand(fallbackMethod = "startTaskFallBack")
    public CommonResult startTask(String uniqueTaskKey) {
        return restTemplate.getForObject("http://service-quartz/startTask/" + uniqueTaskKey, CommonResult.class);
    }

    /**
     * 开启任务 断路器
     *
     * @return boolean
     * @author ZhengYu
     */
    public CommonResult startTaskFallBack(String uniqueTaskKey) {
        return CommonResult.buildCommonResult(ConstantEnum.REMOTE_CALL_FAIL);
    }

    /**
     * 停止任务
     *
     * @param uniqueTaskKey 唯一任务标识
     * @return boolean
     * @author ZhengYu
     */
    @Override
    @HystrixCommand(fallbackMethod = "stopTaskFallBack")
    public CommonResult stopTask(String uniqueTaskKey) {
        return restTemplate.getForObject("http://service-quartz/stopTask/" + uniqueTaskKey, CommonResult.class);
    }

    /**
     * 停止任务 断路器
     *
     * @return boolean
     * @author ZhengYu
     */
    public CommonResult stopTaskFallBack(String uniqueTaskKey) {
        return CommonResult.buildCommonResult(ConstantEnum.REMOTE_CALL_FAIL);
    }
}
