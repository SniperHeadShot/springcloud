package com.bat.springcloud.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.bat.springcloud.user.request.UserInsertSimpleRequest;
import com.bat.springcloud.user.request.UserLoginRequest;
import com.bat.common.response.CommonResult;
import com.bat.springcloud.user.service.AccountService;
import com.bat.springcloud.user.util.VerificationCodeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;

/**
 * @ClassName AccountLoginApi
 * @Description 账户登陆服务
 * @Author ZhengYu
 * @Version: 1.0
 * @Create: 2019/5/24 18:48
 **/
@Api(value = "AccountLoginApi", tags = {"login-api"}, description = "账户登陆服务")
@RestController
public class AccountApi {

    private static final Logger log = LoggerFactory.getLogger(AccountApi.class);

    @Autowired
    private AccountService accountService;

    @ApiOperation(value = "用户登陆校验", notes = "用户登陆校验")
    @GetMapping("/users/check")
    public CommonResult loginCheck(UserLoginRequest userLoginRequest) {
        long startTime = System.currentTimeMillis();
        log.info("校验用户登陆 AccountLoginApi.loginCheck ======> 入参：{}", JSONObject.toJSONString(userLoginRequest));
        CommonResult commonResult = this.accountService.checkAccount(userLoginRequest);
        log.info("校验用户登陆 AccountLoginApi.loginCheck <====== 结果:{}; 耗时:{}ms", JSONObject.toJSONString(commonResult), Instant.now().plusMillis(~startTime).toEpochMilli());
        return commonResult;
    }

    @ApiOperation(value = "账号注册", notes = "账号注册")
    @PostMapping("/account/register")
    public CommonResult registerAccount(@RequestBody UserInsertSimpleRequest userInsertSimpleRequest) {
        long startTime = System.currentTimeMillis();
        log.info("账号注册 UserApi.registerAccount ======> 入参：{}", JSONObject.toJSONString(userInsertSimpleRequest));
        CommonResult commonResult = this.accountService.registerAccount(userInsertSimpleRequest);
        log.info("账号注册 UserApi.registerAccount <====== 结果:{}; 耗时:{}ms", JSONObject.toJSONString(commonResult), Instant.now().plusMillis(~startTime).toEpochMilli());
        return commonResult;
    }

    @ApiOperation(value = "获取验证码", notes = "获取验证码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "accountName", value = "用户账号", required = true, paramType = "path", dataType = "string")
    })
    @GetMapping("/account/verificationCode/{accountName}")
    public void getVerificationCode(@PathVariable String accountName, HttpServletResponse response) throws IOException {
        long startTime = System.currentTimeMillis();
        log.info("获取验证码 UserApi.getVerificationCode ======> 入参：{}", accountName);
        String verificationCode = this.accountService.createVerificationCode(accountName);
        VerificationCodeUtil.drawVerificationCodeText(verificationCode, response);
        log.info("获取验证码 UserApi.getVerificationCode <====== 耗时:{}ms", Instant.now().plusMillis(~startTime).toEpochMilli());
    }
}
