package com.bat.feign.controller;

import com.bat.commoncode.entity.CustomStructure;
import com.bat.commoncode.enums.ConstantEnum;
import com.bat.commoncode.response.CommonResult;
import com.bat.commoncode.util.UuidUtils;
import com.bat.feign.client.CustomClient;
import com.bat.feign.client.CustomFileUploadClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Random;

/**
 * Feign Controller
 *
 * @author ZhengYu
 * @version 1.0 2020/4/17 15:21
 **/
@RestController
@RequestMapping("/feign")
public class FeignController {

    @Autowired
    private CustomClient customClient;

    @Autowired
    private CustomFileUploadClient customFileUploadClient;

    @GetMapping("/resource/{resourceUuid}")
    @HystrixCommand(fallbackMethod = "hystrixGetFeignStruct")
    public CommonResult getFeignStruct(@PathVariable String resourceUuid) {
        if (new Random().nextBoolean()){
            throw new RuntimeException("Surprise");
        }
        return customClient.getFeignStruct(resourceUuid, UuidUtils.getRandomUuid());
    }

    public CommonResult hystrixGetFeignStruct(@PathVariable String resourceUuid) {
        return CommonResult.buildCommonResult(ConstantEnum.GLOBAL_SUCCESS, String.format("[%s]不存在", resourceUuid), null);
    }

    @GetMapping("/pojo")
    public CommonResult feignGetPojo(CustomStructure customStructure) {
        return customClient.feignGetPojo(customStructure);
    }

    @PostMapping(value = "/upload")
    public CommonResult feignUpload(@RequestParam(value = "file", required = false) MultipartFile file,
                                    @RequestParam(value = "name", required = false) String name,
                                    @RequestParam(value = "value", required = false) String value) {
        return customFileUploadClient.feignUpload(file, name, value);
    }
}
