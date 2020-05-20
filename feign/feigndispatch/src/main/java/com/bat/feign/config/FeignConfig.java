package com.bat.feign.config;

import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * FeignConfig
 *
 * @author ZhengYu
 * @version 1.0 2020/4/17 15:15
 **/
@Configuration
public class FeignConfig {

//    @Bean
//    public Retryer feignRetryer() {
//        return new Retryer.Default(100, SECONDS.toMillis(1), 5);
//    }

//    @Bean
//    public Encoder multipartFormEncoder() {
//        return new SpringFormEncoder();
//    }
//
//    @Bean
//    public feign.Logger.Level multipartLoggerLevel() {
//        return feign.Logger.Level.FULL;
//    }

//    @Bean
//    public Contract feignContract(){
//        return new Contract.Default();
//    }
//
//    @Bean
//    public Encoder feignSpringFormEncoder(){
//        return new SpringFormEncoder();
//    }
}
