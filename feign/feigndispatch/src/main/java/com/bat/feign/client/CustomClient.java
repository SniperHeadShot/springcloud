package com.bat.feign.client;

import com.bat.commoncode.entity.CustomStructure;
import com.bat.commoncode.response.CommonResult;
import com.bat.feign.client.fallback.CustomClientFallback;
import com.bat.feign.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Feign Client
 *
 * @author ZhengYu
 * @version 1.0 2020/4/17 15:16
 **/
@Primary
@FeignClient(value = "CUSTOM-SERVICE", configuration = FeignConfig.class, fallback = CustomClientFallback.class, path = "/service/inner")
public interface CustomClient {

    /**
     * 测试基础的feign功能
     *
     * @param resourceUuid 资源UUID
     * @return com.bat.commoncode.response.CommonResult
     * @author ZhengYu
     */
    @GetMapping("/resource/{resourceUuid}")
    CommonResult getFeignStruct(@PathVariable String resourceUuid, @RequestParam(value = "param", required = false) String param);

    /**
     * 接收feign发送get请求的pojo对象
     *
     * @param customStructure pojo
     * @return com.bat.commoncode.response.CommonResult
     * @author ZhengYu
     */
    @GetMapping("/feign/pojo")
    CommonResult feignGetPojo(CustomStructure customStructure);
}
