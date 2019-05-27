package com.bat.springcloud.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName UserInsertSimpleRequest
 * @Description 用户注册
 * @Author ZhengYu
 * @Version: 1.0
 * @Create: 2019/5/27 13:51
 **/
@ApiModel("用户注册请求参数")
@Data
public class UserInsertSimpleRequest {

    @ApiModelProperty(value = "用户名", required = true)
    private String accountName;

    @ApiModelProperty(value = "密码", required = true)
    private String accountPassword;
}
