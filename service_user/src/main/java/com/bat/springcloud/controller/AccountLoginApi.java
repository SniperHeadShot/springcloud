package com.bat.springcloud.controller;

import com.alibaba.fastjson.JSONObject;
import com.bat.springcloud.enums.ConstantEnum;
import com.bat.springcloud.request.UserLoginRequest;
import com.bat.springcloud.response.CommonResult;
import com.bat.springcloud.service.LoginService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

/**
 * @ClassName AccountLoginApi
 * @Description 账户登陆服务
 * @Author ZhengYu
 * @Version: 1.0
 * @Create: 2019/5/24 18:48
 **/
@Api(value = "AccountLoginApi", tags = {"login-api"}, description = "账户登陆服务")
@Slf4j
@RestController
public class AccountLoginApi {

    @Autowired
    private LoginService loginService;

    @GetMapping("/users/check")
    public CommonResult loginCheck(UserLoginRequest userLoginRequest) {
        long startTime = System.currentTimeMillis();
        log.info("校验用户登陆 AccountLoginApi.loginCheck 入参 --->", JSONObject.toJSONString(userLoginRequest));
        CommonResult commonResult;
        try {
            commonResult = this.loginService.checkAccount(userLoginRequest);
        } catch (Exception e) {
            log.error("校验用户登陆 AccountLoginApi.loginCheck 出错", e);
            e.printStackTrace();
            commonResult = new CommonResult(ConstantEnum.GLOBAL_FAIL);
        }
        log.info("校验用户登陆 AccountLoginApi.loginCheck <--- 结果:{}; 耗时:{}ms", JSONObject.toJSONString(commonResult), Instant.now().plusMillis(~startTime).toEpochMilli());
        return commonResult;
    }

}
