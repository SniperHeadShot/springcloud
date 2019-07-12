package com.bat.springcloud.upms.ribbon.api;

import com.bat.common.response.CommonResult;
import com.bat.springcloud.upms.ribbon.service.QuartzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 定时任务调用 API
 *
 * @author ZhengYu
 * @version 1.0 2019/7/12 10:29
 **/

@RestController
public class QuartzApi {

    @Autowired
    private QuartzService quartzService;

    @GetMapping("/startTask/{uniqueTaskKey}")
    public CommonResult startTask(@PathVariable(value = "uniqueTaskKey") String uniqueTaskKey) {
        return quartzService.startTask(uniqueTaskKey);
    }

    @GetMapping("/stopTask/{uniqueTaskKey}")
    public CommonResult stopTask(@PathVariable(value = "uniqueTaskKey") String uniqueTaskKey) {
        return quartzService.stopTask(uniqueTaskKey);
    }
}
