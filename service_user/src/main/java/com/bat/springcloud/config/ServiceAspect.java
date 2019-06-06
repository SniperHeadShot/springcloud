package com.bat.springcloud.config;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Arrays;
import java.util.Date;

/**
 * @ClassName ServiceAspect
 * @Description 服务层调用持久层获取数据切面配置类
 * @Author ZhengYu
 * @Version: 1.0
 * @Create: 2019/6/6 13:29
 **/
@Aspect
@Component
public class ServiceAspect {

    private Logger logger = LoggerFactory.getLogger(ServiceAspect.class);

    @Pointcut("execution(*  com.bat.springcloud.dao..*.*(..))")
    public void executeMethodCut() {
    }

    @Around("executeMethodCut()")
    public Object executeServiceAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        logger.info("==================== Aspect Method Start ====================");
        // 方法描述
        String methodLongDesc = proceedingJoinPoint.getSignature().toLongString();
        // 方法参数
        String methodParam = Arrays.toString(proceedingJoinPoint.getArgs());
        long startTime = (new Date()).getTime();
        // 方法执行
        Object proceed = proceedingJoinPoint.proceed();
        String methodReturn = JSONObject.toJSONString(proceed);
        long endTime = Instant.now().plusMillis(~startTime).toEpochMilli();

        String logMethod = System.getProperty("line.separator")
                + "method description:" + System.getProperty("line.separator")
                + "method body    ===> {}" + System.getProperty("line.separator")
                + "method param   ===> {}" + System.getProperty("line.separator")
                + "method return:" + System.getProperty("line.separator")
                + "return         <=== {}" + System.getProperty("line.separator")
                + "time consuming ===> {} ms";
        logger.info(logMethod, methodLongDesc, methodParam, methodReturn, endTime);

        logger.info("==================== Aspect Method End ====================");
        return proceed;
    }
}
