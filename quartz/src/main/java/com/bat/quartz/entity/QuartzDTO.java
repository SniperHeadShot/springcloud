package com.bat.quartz.entity;

import lombok.Data;
import org.quartz.Job;

/**
 * 定时任务传参
 *
 * @author ZhengYu
 * @version 1.0 2019/7/11 15:17
 **/
@Data
public class QuartzDTO {

    private String jobName;

    private String jobGroup;

    private String triggerName;

    private String triggerGroup;

    private Class<? extends Job> jobClass;

    public QuartzDTO(String jobName) {
        this.jobName = jobName;
    }

    public QuartzDTO(String jobName, String triggerName) {
        this.jobName = jobName;
        this.triggerName = triggerName;
    }

    public QuartzDTO(String jobName, String jobGroup, String triggerName, String triggerGroup) {
        this.jobName = jobName;
        this.jobGroup = jobGroup;
        this.triggerName = triggerName;
        this.triggerGroup = triggerGroup;
    }

    public String getJobName() {
        return jobName;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public String getTriggerName() {
        return triggerName;
    }

    public String getTriggerGroup() {
        return triggerGroup;
    }

    public Class<? extends Job> getJobClass() {
        return jobClass;
    }

    public void setJobClass(Class<? extends Job> jobClass) {
        this.jobClass = jobClass;
    }


}
