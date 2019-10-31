package com.bat.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Feign 基础API
 *
 * @author ZhengYu
 * @version 1.0 2019/10/31 15:12
 **/
@EnableFeignClients
@SpringBootApplication
public class FeignModule01Application {
    public static void main(String[] args) {
        SpringApplication.run(FeignModule01Application.class, args);
    }
}