package com.bat.commoncode.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 日志系统 - AOP切面方法描述
 *
 * @author ZhengYu
 * @version 1.0 2019/6/22 16:39
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CustomMethodDesc {

    String value();

}
