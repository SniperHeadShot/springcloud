package com.bat.commoncode.exceptions;

/**
 * 参数校验未通过通用异常
 *
 * @author ZhengYu
 * @version 1.0 2019/6/22 16:39
 **/
public class ParameterVerificationNotPassException extends RuntimeException {

    /**
     * 参数校验未通过通用异常
     *
     * @author ZhengYu
     */
    public ParameterVerificationNotPassException(String message) {
        super(message);
    }
}
