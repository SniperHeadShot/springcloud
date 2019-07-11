package com.bat.quartz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 分布式定时任务解决方案
 *
 * @author ZhengYu
 * @version 1.0 2019/7/10 20:30
 **/
@EnableEurekaClient
@SpringBootApplication
public class QuartzApplication {
    public static void main(String[] args) {
        SpringApplication.run(QuartzApplication.class, args);
    }
}
