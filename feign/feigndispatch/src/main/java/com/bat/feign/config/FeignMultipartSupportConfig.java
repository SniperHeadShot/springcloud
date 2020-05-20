package com.bat.feign.config;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.context.annotation.Bean;

/**
 * Feign File Form Upload Config
 *
 * @author ZhengYu
 * @version 1.0 2020/5/20 10:25
 **/
public class FeignMultipartSupportConfig {

    @Bean
    public Encoder multipartFormEncoder() {
        return new SpringFormEncoder();
    }
}
