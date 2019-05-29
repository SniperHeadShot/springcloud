package com.bat.springcloud.controller;

import com.alibaba.fastjson.JSONObject;
import com.bat.springcloud.enums.ConstantEnum;
import com.bat.springcloud.request.UserInsertSimpleRequest;
import com.bat.springcloud.request.UserLoginRequest;
import com.bat.springcloud.response.CommonResult;
import com.bat.springcloud.service.AccountService;
import com.bat.springcloud.util.VerificationCodeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
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
public class AccountApi {

    @Autowired
    private AccountService accountService;

    @ApiOperation(value = "用户登陆校验", notes = "用户登陆校验")
    @GetMapping("/users/check")
    public CommonResult loginCheck(UserLoginRequest userLoginRequest) {
        long startTime = System.currentTimeMillis();
        log.info("校验用户登陆 AccountLoginApi.loginCheck ======> 入参：{}", JSONObject.toJSONString(userLoginRequest));
        CommonResult commonResult;
        try {
            commonResult = accountService.checkAccount(userLoginRequest);
        } catch (Exception e) {
            log.error("校验用户登陆 AccountLoginApi.loginCheck 出错", e);
            commonResult = CommonResult.buildCommonResult(ConstantEnum.GLOBAL_FAIL);
        }
        log.info("校验用户登陆 AccountLoginApi.loginCheck <====== 结果:{}; 耗时:{}ms", JSONObject.toJSONString(commonResult), Instant.now().plusMillis(~startTime).toEpochMilli());
        return commonResult;
    }

    @ApiOperation(value = "账号注册", notes = "账号注册")
    @PostMapping("/account/register")
    public CommonResult registerAccount(@RequestBody UserInsertSimpleRequest userInsertSimpleRequest) {
        long startTime = System.currentTimeMillis();
        log.info("账号注册 UserApi.registerAccount ======> 入参：{}", JSONObject.toJSONString(userInsertSimpleRequest));
        CommonResult commonResult;
        try {
            commonResult = accountService.registerAccount(userInsertSimpleRequest);
        } catch (Exception e) {
            log.error("账号注册 UserApi.registerAccount 出错", e);
            commonResult = CommonResult.buildCommonResult(ConstantEnum.GLOBAL_FAIL);
        }
        log.info("账号注册 UserApi.registerAccount <====== 结果:{}; 耗时:{}ms", JSONObject.toJSONString(commonResult), Instant.now().plusMillis(~startTime).toEpochMilli());
        return commonResult;
    }

    @ApiOperation(value = "获取验证码", notes = "获取验证码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "accountName", value = "用户账号", required = true, paramType = "path", dataType = "string")
    })
    @GetMapping("/account/verificationCode/{accountName}")
    public void getVerificationCode(@PathVariable String accountName, HttpServletResponse response) {
        long startTime = System.currentTimeMillis();
        log.info("获取验证码 UserApi.getVerificationCode ======> 入参：{}", accountName);
        try {
            String verificationCode = accountService.createVerificationCode(accountName);
            VerificationCodeUtil.drawVerificationCodeText(verificationCode, response);
        } catch (Exception e) {
            log.error("获取验证码 UserApi.getVerificationCode 出错", e);
        }
        log.info("获取验证码 UserApi.getVerificationCode <====== 耗时:{}ms", Instant.now().plusMillis(~startTime).toEpochMilli());
    }
}
