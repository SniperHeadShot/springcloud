package com.bat.springcloud.upms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * 远程调用
 *
 * @author ZhengYu
 * @version 1.0 2019/7/11 14:44
 **/
@EnableEurekaClient
@EnableHystrix
@SpringBootApplication
public class RemoteServiceCallApplication {
    public static void main(String[] args) {
        SpringApplication.run(RemoteServiceCallApplication.class, args);
    }
}
