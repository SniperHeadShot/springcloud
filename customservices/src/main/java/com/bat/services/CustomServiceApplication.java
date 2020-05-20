package com.bat.services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 业务服务
 *
 * @author ZhengYu
 * @version 1.0 2020/4/13 17:03
 **/
@EnableDiscoveryClient
@SpringBootApplication
public class CustomServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomServiceApplication.class, args);
    }
}
