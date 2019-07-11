package com.bat.quartz.task;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

/**
 * 定时任务实现类
 *
 * @author ZhengYu
 * @version 1.0 2019/7/11 1:16
 **/
@Slf4j
public class TestJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        log.info("定时任务执行 参数=[{}], 端口=[{}]", jobExecutionContext.getJobDetail().getJobDataMap().get("projectUuid"));
    }
}
