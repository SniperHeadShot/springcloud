package com.bat.quartz.controller;

import com.bat.common.enums.ConstantEnum;
import com.bat.common.response.CommonResult;
import com.bat.quartz.service.QuartzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 定时任务
 *
 * @author ZhengYu
 * @version 1.0 2019/7/11 14:44
 **/
@Slf4j
@RestController
public class QuartzController {

    @Autowired
    private QuartzService quartzService;

    @GetMapping("/startTask/{uniqueTaskKey}")
    public CommonResult startTask(@PathVariable(value = "uniqueTaskKey") String uniqueTaskKey) {
        return CommonResult.buildCommonResult(quartzService.startTask(uniqueTaskKey) ? ConstantEnum.GLOBAL_SUCCESS : ConstantEnum.GLOBAL_FAIL);
    }

    @GetMapping("/stopTask/{uniqueTaskKey}")
    public CommonResult stopTask(@PathVariable(value = "uniqueTaskKey") String uniqueTaskKey) {
        return CommonResult.buildCommonResult(quartzService.stopTask(uniqueTaskKey) ? ConstantEnum.GLOBAL_SUCCESS : ConstantEnum.GLOBAL_FAIL);
    }
}
