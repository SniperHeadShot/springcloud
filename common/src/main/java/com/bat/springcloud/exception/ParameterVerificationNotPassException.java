package com.bat.springcloud.exception;

/**
 * @ClassName ParameterVerificationNotPassException
 * @Description 参数校验未通过通用异常
 * @Author ZhengYu
 * @Version: 1.0
 * @Create: 2019/5/24 18:43
 **/
public class ParameterVerificationNotPassException extends RuntimeException {

    /**
     * @Param [message]
     * @Return
     * @Author ZhengYu
     * @Description: 参数校验未通过通用异常
     * @Date 2019/5/24
     */
    public ParameterVerificationNotPassException(String message) {
        super(message);
    }
}
