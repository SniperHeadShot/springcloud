package com.bat.eureka.client.config;

import com.bat.commoncode.annotation.CustomMethodDesc;
import com.bat.commoncode.enums.ConstantEnum;
import com.bat.commoncode.response.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;

/**
 * 全局异常处理
 *
 * @author ZhengYu
 * @version 1.0 2019/11/1 14:10
 **/
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 运行时异常处理
     *
     * @param ex 运行时异常
     * @return com.bat.commoncode.response.CommonResult
     * @author ZhengYu
     */
    @ExceptionHandler(RuntimeException.class)
    public CommonResult defaultRuntimeExceptionHandler(RuntimeException ex, HandlerMethod handlerMethod) {
        CustomMethodDesc customMethodDesc = handlerMethod.getMethod().getDeclaredAnnotation(CustomMethodDesc.class);
        if (customMethodDesc != null && StringUtils.isNotEmpty(customMethodDesc.value())) {
            log.error("[{}] 报错 [{}] [{}]", customMethodDesc.value(), ex.getMessage(), ex);
        }
        return CommonResult.buildCommonResult(ConstantEnum.GLOBAL_FAIL, ex.getMessage());
    }

    /**
     * 异常处理
     *
     * @param ex 异常
     * @return com.bat.commoncode.response.CommonResult
     * @author ZhengYu
     */
    @ExceptionHandler(Exception.class)
    public CommonResult defaultExceptionHandler(Exception ex) {
        return CommonResult.buildCommonResult(ConstantEnum.GLOBAL_FAIL, ex.getMessage());
    }
}
