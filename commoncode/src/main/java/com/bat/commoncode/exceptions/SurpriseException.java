package com.bat.commoncode.exceptions;

import com.bat.commoncode.enums.ConstantEnum;

/**
 * 程序中的小惊喜
 *
 * @author ZhengYu
 * @version 1.0 2019/10/31 17:19
 **/
public class SurpriseException extends RuntimeException {

    public SurpriseException() {
        super(ConstantEnum.GLOBAL_SURPRISE.msg());
    }
}
