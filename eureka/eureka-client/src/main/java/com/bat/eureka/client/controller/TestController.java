package com.bat.eureka.client.controller;

import com.bat.commoncode.enums.ConstantEnum;
import com.bat.commoncode.response.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试 Controller
 *
 * @author ZhengYu
 * @version 1.0 2019/10/30 14:57
 **/
@RestController
public class TestController {

    @GetMapping("/test")
    public CommonResult test() {
        return CommonResult.buildCommonResult(ConstantEnum.GLOBAL_SUCCESS);
    }

}
