package com.bat.springcloud.upms.ribbon.service.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 将 RestTemplate 交给 spring 管理
 *
 * @author ZhengYu
 * @version 1.0 2019/7/12 10:36
 **/
@Configuration
public class BeanConfig {

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}