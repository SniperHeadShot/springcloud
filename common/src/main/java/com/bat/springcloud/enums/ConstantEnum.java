package com.bat.springcloud.enums;

import com.bat.springcloud.annotation.CodeEntity;
import com.bat.springcloud.response.CodeEnumInterface;

/**
 * @ClassName ConstantEnum
 * @Description 返回值枚举
 * @Author ZhengYu
 * @Version: 1.0
 * @Create: 2019/5/24 18:09
 **/
public enum ConstantEnum implements CodeEnumInterface {

    @CodeEntity(
            success = true,
            msg = "操作成功!"
    )
    GLOBAL_SUCCESS,
    @CodeEntity(
            success = false,
            errCode = -1,
            msg = "系统繁忙，请稍后再试!"
    )
    GLOBAL_FAIL,
    @CodeEntity(
            success = false,
            errCode = 4000,
            msg = "参数不完整，请检查后再试!"
    )
    PARAMETER_VERIFICATION_FAIL
}
