package com.bat.springcloud.service;

import com.bat.springcloud.request.UserLoginRequest;
import com.bat.springcloud.response.CommonResult;

/**
 * @ClassName LoginService
 * @Description 登陆服务
 * @Author ZhengYu
 * @Version: 1.0
 * @Create: 2019/5/24 18:54
 **/
public interface LoginService {

    /**
     * @Param [userLoginRequest]
     * @Return com.bat.springcloud.response.CommonResult
     * @Author ZhengYu
     * @Description: 校验验证码及账号密码是否正确
     * @Date 2019/5/24
     */
    CommonResult checkAccount(UserLoginRequest userLoginRequest);

}
