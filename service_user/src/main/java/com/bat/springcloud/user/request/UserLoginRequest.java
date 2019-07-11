package com.bat.springcloud.user.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName UserLoginRequest
 * @Description 用户登陆请求参数
 * @Author ZhengYu
 * @Version: 1.0
 * @Create: 2019/5/24 18:48
 **/
@ApiModel("用户登陆请求参数")
@Data
public class UserLoginRequest {

    @ApiModelProperty(value = "用户名", required = true)
    private String username;

    @ApiModelProperty(value = "密码", required = true)
    private String password;

    @ApiModelProperty(value = "验证码", required = true)
    private String verificationCode;

}
