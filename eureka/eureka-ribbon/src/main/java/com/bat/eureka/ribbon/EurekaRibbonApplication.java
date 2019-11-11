package com.bat.eureka.ribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 客户端负载均衡 Ribbon
 *
 * @author ZhengYu
 * @version 1.0 2019/11/11 20:46
 **/
@EnableDiscoveryClient
@SpringBootApplication
public class EurekaRibbonApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaRibbonApplication.class, args);
    }
}
