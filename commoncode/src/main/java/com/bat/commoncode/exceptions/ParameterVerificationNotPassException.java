package com.bat.commoncode.exceptions;

import com.bat.commoncode.enums.ConstantEnum;

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
    public ParameterVerificationNotPassException() {
        super(ConstantEnum.PARAMETER_VERIFICATION_FAIL.msg());
    }
}
