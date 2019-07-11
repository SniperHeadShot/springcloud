package com.bat.quartz.config;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Component;

/**
 * 定时任务管理类
 *
 * @author ZhengYu
 * @version 1.0 2019/7/10 20:32
 **/
@Component
public class QuartzManager {

    private SchedulerFactory schedulerFactory = new StdSchedulerFactory();

    public void addJob(Class<? extends Job> jobClass, String projectUuid) throws SchedulerException {
        Scheduler scheduler = schedulerFactory.getScheduler();
        JobDetail jobDetail = JobBuilder.newJob(jobClass)
                .withIdentity("job_" + projectUuid, "jobGroup_" + projectUuid)
                .build();
        jobDetail.getJobDataMap().put("projectUuid", projectUuid);
        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger_" + projectUuid, "triggerGroup_" + projectUuid)
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule("0/3 * * * * ?"))
                .build();
        scheduler.scheduleJob(jobDetail, cronTrigger);

        if (!scheduler.isShutdown()) {
            scheduler.start();
        }
    }

    public void removeJob(String projectUuid) throws SchedulerException {
        Scheduler scheduler = schedulerFactory.getScheduler();
        TriggerKey triggerKey = TriggerKey.triggerKey("trigger_" + projectUuid, "triggerGroup_" + projectUuid);
        scheduler.pauseTrigger(triggerKey);
        scheduler.unscheduleJob(triggerKey);
        scheduler.deleteJob(JobKey.jobKey("job_" + projectUuid, "jobGroup_" + projectUuid));
    }

    /**
     * 生成唯一任务名
     *
     * @param uniqueJobKey 唯一
     * @return java.lang.String
     * @author ZhengYu
     */
    private String getJobName(String uniqueJobKey) {
        return "job_" + uniqueJobKey;
    }

    /**
     * 生成唯一任务群组名
     *
     * @param uniqueJobKey 唯一
     * @return java.lang.String
     * @author ZhengYu
     */
    private String getJobGroup(String uniqueJobKey) {
        return "jobGroup_" + uniqueJobKey;
    }

    /**
     * 生成唯一触发器名
     *
     * @param uniqueJobKey 唯一
     * @return java.lang.String
     * @author ZhengYu
     */
    private String getTriggerName(String uniqueJobKey) {
        return "trigger_" + uniqueJobKey;
    }

    /**
     * 生成唯一触发器组名
     *
     * @param uniqueJobKey 唯一
     * @return java.lang.String
     * @author ZhengYu
     */
    private String getTriggerGroupName(String uniqueJobKey) {
        return "triggerGroup_" + uniqueJobKey;
    }
}
