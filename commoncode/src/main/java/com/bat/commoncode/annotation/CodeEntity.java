package com.bat.commoncode.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 响应结构体注解
 *
 * @author ZhengYu
 * @version 1.0 2019/10/30 11:53
 **/
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CodeEntity {

    boolean success();

    int errCode() default 0;

    String msg();
}
