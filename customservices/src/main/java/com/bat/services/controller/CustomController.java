package com.bat.services.controller;

import com.bat.commoncode.entity.CustomStructure;
import com.bat.commoncode.entity.FeignStruct;
import com.bat.commoncode.enums.ConstantEnum;
import com.bat.commoncode.response.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 提供内部调用的服务
 *
 * @author ZhengYu
 * @version 1.0 2020/4/13 19:35
 **/
@Slf4j
@RestController
@RequestMapping("/service/inner")
public class CustomController {

    @Value("${server.port}")
    private Integer serverPort;

    /**
     * 测试基础的feign功能
     *
     * @param resourceUuid 资源UUID
     * @return com.bat.commoncode.response.CommonResult
     * @author ZhengYu
     */
    @GetMapping("/resource/{resourceUuid}")
    public CommonResult getFeignStruct(@PathVariable("resourceUuid") String resourceUuid, @RequestParam(value = "param", required = false) String param) {
        FeignStruct feignStruct = new FeignStruct();
        feignStruct.setResourceUuid(resourceUuid);
        feignStruct.setServerPort(serverPort);
        feignStruct.setParam(param);
        return CommonResult.buildCommonResult(ConstantEnum.GLOBAL_SUCCESS, feignStruct);
    }

    /**
     * 接收feign发送get请求的pojo对象
     *
     * @param customStructure pojo
     * @return com.bat.commoncode.response.CommonResult
     * @author ZhengYu
     */
    @GetMapping("/feign/pojo")
    public CommonResult feignGetPojo(CustomStructure customStructure) {
        return CommonResult.buildCommonResult(ConstantEnum.GLOBAL_SUCCESS, customStructure);
    }

    /**
     * 接收feign发送的param和文件信息
     *
     * @param file  文件
     * @param name  param
     * @param value param
     * @return com.bat.commoncode.response.CommonResult
     * @author ZhengYu
     */
    @PostMapping(value = "/feign/upload")
    public CommonResult feignUpload(@RequestParam(value = "file", required = false) MultipartFile file,
                                    @RequestParam(value = "name", required = false) String name,
                                    @RequestParam(value = "value", required = false) String value) {
        log.info("接收feign发送的param name=[{}], value=[{}], 文件名=[{}], 文件大小=[{}]", name, value, file.getOriginalFilename(), file.getSize());
        return CommonResult.buildCommonResult(ConstantEnum.GLOBAL_SUCCESS);
    }
}
