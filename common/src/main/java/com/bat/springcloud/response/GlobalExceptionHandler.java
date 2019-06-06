package com.bat.springcloud.response;

import com.bat.springcloud.enums.ConstantEnum;
import com.bat.springcloud.exception.ParameterVerificationNotPassException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName GlobalExceptionHandler
 * @Description 全局错误处理
 * @Author ZhengYu
 * @Version: 1.0
 * @Create: 2019/6/4 16:49
 **/
@ControllerAdvice
public class GlobalExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public CommonResult defaultErrorHandler(HttpServletRequest httpServletRequest, Exception exception) throws IOException {
        logger.error("==================== Exception Message Start ====================");
        // 请求 URL
        logger.error("reqUrl   ======>: {}", httpServletRequest.getRequestURL());
        // 请求头
        Map<String, Object> requestHeadersMap = new ConcurrentHashMap<>(16);
        Enumeration<String> requestHeaderNames = httpServletRequest.getHeaderNames();
        while (requestHeaderNames.hasMoreElements()) {
            String headerName = requestHeaderNames.nextElement();
            requestHeadersMap.put(headerName, httpServletRequest.getHeader(headerName));
        }
        logger.error("header   ========>: {}", requestHeadersMap.toString());
        // 请求参数
        logger.error("params   ========>: {}", httpServletRequest.getQueryString());
        // 请求体
        BufferedReader bufferedReader = httpServletRequest.getReader();
        StringBuilder requestBodyText = new StringBuilder();
        String readLine;
        while (!StringUtils.isEmpty(readLine = bufferedReader.readLine())) {
            requestBodyText.append(readLine);
        }
        logger.error("body     ========>: {}", requestBodyText.toString());
        // 异常信息
        logger.error("errorMsg ========>: {}", exception.getMessage());
        logger.error("", exception);
        logger.error("==================== Exception Message End ====================");

        if (exception instanceof ParameterVerificationNotPassException) {
            return CommonResult.buildCommonResult(ConstantEnum.PARAMETER_VERIFICATION_FAIL, exception.getMessage());
        }
        return CommonResult.buildCommonResult(ConstantEnum.GLOBAL_FAIL);
    }
}
