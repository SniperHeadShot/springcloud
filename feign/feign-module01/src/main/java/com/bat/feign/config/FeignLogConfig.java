package com.bat.feign.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Feign 日志配置
 *
 * @author ZhengYu
 * @version 1.0 2019/11/1 13:32
 **/
@Configuration
public class FeignLogConfig {

    /**
     * Logger.Level
     *  NONE: No logging
     *  BASIC: Log only the request method and URL and the response status code and execution time
     *  HEADERS: Log the basic information along with request and response headers.
     *  FULL: Log the headers, body, and metadata for both requests and responses
     *
     * @return feign.Logger.Level
     * @author ZhengYu
     */
    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }
}
