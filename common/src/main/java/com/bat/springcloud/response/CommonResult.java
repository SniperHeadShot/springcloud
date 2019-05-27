package com.bat.springcloud.response;

import com.bat.springcloud.enums.ConstantEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName CommonResult
 * @Description 通用返回结构体
 * @Author ZhengYu
 * @Version: 1.0
 * @Create: 2019/5/24 17:41
 **/

@ApiModel("响应结构体")
@Data
public class CommonResult<T> {

    @ApiModelProperty(value = "操作是否成功")
    private Boolean success;
    @ApiModelProperty(value = "操作状态码")
    private Integer code;
    @ApiModelProperty(value = "返回信息")
    private String msg;
    @ApiModelProperty(value = "数据")
    private T data;

    private CommonResult(ConstantEnum constantEnum) {
        this.success = constantEnum.success();
        this.code = constantEnum.errCode();
        this.msg = constantEnum.msg();
    }

    private CommonResult(ConstantEnum constantEnum, String msg) {
        this.success = constantEnum.success();
        this.code = constantEnum.errCode();
        this.msg = msg;
    }

    public static CommonResult buildCommonResult(ConstantEnum constantEnum) {
        return new CommonResult(constantEnum);
    }

    public static CommonResult buildCommonResult(ConstantEnum constantEnum, String msg) {
        return new CommonResult(constantEnum, msg);
    }
}
