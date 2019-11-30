package com.bat.eureka.client.controller;

import com.alibaba.fastjson.JSONObject;
import com.bat.commoncode.annotation.CustomMethodDesc;
import com.bat.commoncode.entity.CustomStructure;
import com.bat.commoncode.enums.ConstantEnum;
import com.bat.commoncode.exceptions.SurpriseException;
import com.bat.commoncode.response.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Random;

/**
 * 测试 Controller
 *
 * @author ZhengYu
 * @version 1.0 2019/10/30 14:57
 **/
@Slf4j
@RestController
@RequestMapping("/custom")
public class CustomController {

    @Value("${server.port}")
    private String serverPort;

    @CustomMethodDesc("GET 方法")
    @GetMapping("/get")
    public CommonResult customGet(HttpServletRequest request) {
        parseRequestParam(request, null);
        buildSurprise();
        return CommonResult.buildCommonResult(ConstantEnum.GLOBAL_SUCCESS, serverPort);
    }

    @CustomMethodDesc("POST 方法")
    @PostMapping("/post")
    public CommonResult customPost(@RequestBody CustomStructure customStructure, HttpServletRequest request) {
        parseRequestParam(request, customStructure);
        buildSurprise();
        return CommonResult.buildCommonResult(ConstantEnum.GLOBAL_SUCCESS, serverPort);
    }

    @CustomMethodDesc("PUT 方法")
    @PutMapping("/put")
    public CommonResult customPut(@RequestBody CustomStructure customStructure, HttpServletRequest request) {
        parseRequestParam(request, customStructure);
        buildSurprise();
        return CommonResult.buildCommonResult(ConstantEnum.GLOBAL_SUCCESS, serverPort);
    }

    @CustomMethodDesc("DELETE 方法")
    @DeleteMapping("/delete")
    public CommonResult customDelete(@RequestBody CustomStructure customStructure, HttpServletRequest request) {
        parseRequestParam(request, customStructure);
        buildSurprise();
        return CommonResult.buildCommonResult(ConstantEnum.GLOBAL_SUCCESS, serverPort);
    }

    /**
     * 随机产生一个异常
     *
     * @author ZhengYu
     */
    private void buildSurprise() {
        if (new Random().nextBoolean()) {
            throw new SurpriseException();
        }
    }

    /**
     * 解析Http请求参数
     *
     * @param request request
     * @param o       请求体
     * @author ZhengYu
     */
    private void parseRequestParam(HttpServletRequest request, Object o) {
        // 读取请求参数
        Enumeration<String> parameterNameEnumeration = request.getParameterNames();
        while (parameterNameEnumeration.hasMoreElements()) {
            String paramKey = parameterNameEnumeration.nextElement();
            String paramValue = request.getParameter(paramKey);
            log.info("Http请求，请求参数 Key=[{}], Value=[{}]", paramKey, paramValue);
        }
        // 读取 SessionId
        log.info("Http请求，SessionId=[{}]", request.getRequestedSessionId());
        // 读取 Cookie
        if (request.getCookies() != null) {
            Arrays.stream(request.getCookies()).forEach(cookie -> log.info("Http请求，Cookie Key=[{}], Value=[{}]", cookie.getName(), cookie.getValue()));
        }
        // 读取请求头
        Enumeration<String> headersEnumeration = request.getHeaderNames();
        while (headersEnumeration.hasMoreElements()) {
            String headerKey = headersEnumeration.nextElement();
            String headerValue = request.getHeader(headerKey);
            log.info("Http请求，请求头   Key=[{}], Value=[{}]", headerKey, headerValue);
        }
        // 读取请求体
        if (o == null) {
            return;
        }
        log.info("Http请求，请求体   [{}]", JSONObject.toJSONString(o));
    }
}