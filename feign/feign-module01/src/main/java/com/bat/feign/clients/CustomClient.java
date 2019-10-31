package com.bat.feign.clients;

import com.bat.commoncode.entity.CustomStructure;
import com.bat.commoncode.response.CommonResult;
import com.bat.feign.clients.fallback.CustomClientFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * 测试Feign
 *
 * @author ZhengYu
 * @version 1.0 2019/10/31 15:19
 **/
@RequestMapping("/custom")
@FeignClient(name = "custom-client", url = "${custom.clientUrl}", fallbackFactory = CustomClientFallbackFactory.class)
public interface CustomClient {

    /**
     * GET
     *
     * @return com.bat.commoncode.response.CommonResult
     * @author ZhengYu
     */
    @GetMapping("/get")
    CommonResult customGet();

    /**
     * POST
     *
     * @param customStructure 请求体
     * @return com.bat.commoncode.response.CommonResult
     * @author ZhengYu
     */
    @PostMapping("/post")
    CommonResult customPost(CustomStructure customStructure);

    /**
     * PUT
     *
     * @param customStructure 请求体
     * @return com.bat.commoncode.response.CommonResult
     * @author ZhengYu
     */
    @PutMapping("/put")
    CommonResult customPut(CustomStructure customStructure);

    /**
     * DELETE
     *
     * @param customStructure 请求体
     * @return com.bat.commoncode.response.CommonResult
     * @author ZhengYu
     */
    @DeleteMapping("/delete")
    CommonResult customDelete(CustomStructure customStructure);
}
