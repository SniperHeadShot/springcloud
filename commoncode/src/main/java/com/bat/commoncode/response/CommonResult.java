package com.bat.commoncode.response;

import com.bat.commoncode.enums.ConstantEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * 通用返回结构体
 *
 * @author ZhengYu
 * @version 1.0 2019/6/22 16:39
 **/
@Data
public class CommonResult<T> implements Serializable {

    /**
     * 操作是否成功
     */
    private boolean success;
    /**
     * 操作状态码
     */
    private int code;
    /**
     * 返回信息
     */
    private String msg;
    /**
     * 数据
     */
    private T data;

    private CommonResult() {
    }

    private CommonResult(boolean success, Integer code, String msg, T data) {
        this.success = success;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> CommonResult buildCommonResult(boolean success, int code, String msg) {
        return new CommonResult<T>(success, code, msg, null);
    }

    public static <T> CommonResult buildCommonResult(boolean success, int code, String msg, T data) {
        return new CommonResult<T>(success, code, msg, data);
    }

    public static <T> CommonResult<T> buildCommonResult(ConstantEnum constantEnum) {
        return new CommonResult<T>(constantEnum.success(), constantEnum.errCode(), constantEnum.msg(), null);
    }

    public static <T> CommonResult<T> buildCommonResult(ConstantEnum constantEnum, T data) {
        return new CommonResult<T>(constantEnum.success(), constantEnum.errCode(), constantEnum.msg(), data);
    }

    public static <T> CommonResult<T> buildCommonResult(ConstantEnum constantEnum, String msg, T data) {
        return new CommonResult<T>(constantEnum.success(), constantEnum.errCode(), msg, data);
    }
}
