package com.bat.springcloud.constant;

/**
 * @ClassName BaseSystemConfig
 * @Description 一些基础配置
 * @Author ZhengYu
 * @Version: 1.0
 * @Create: 2019/5/30 12:50
 **/
public interface BaseSystemConfig {

    /**
     * 验证码 Redis key 后缀
     */
    String ACCOUNT_VERIFICATION_CODE_REDIS_SUFFIX = "login:verification:code:";

    /**
     * 验证码过期秒数
     */
    long ACCOUNT_VERIFICATION_CODE_TIMEOUT_MILLIONS = 30 * 60;

}
