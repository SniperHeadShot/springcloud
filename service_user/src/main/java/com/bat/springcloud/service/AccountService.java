package com.bat.springcloud.service;

import com.bat.springcloud.request.UserInsertSimpleRequest;
import com.bat.springcloud.request.UserLoginRequest;
import com.bat.springcloud.response.CommonResult;

/**
 * @ClassName AccountService
 * @Description 登陆服务
 * @Author ZhengYu
 * @Version: 1.0
 * @Create: 2019/5/24 18:54
 **/
public interface AccountService {

    /**
     * @Param [userLoginRequest]
     * @Return com.bat.springcloud.response.CommonResult
     * @Author ZhengYu
     * @Description: 校验验证码及账号密码是否正确
     * @Date 2019/5/24
     */
    CommonResult checkAccount(UserLoginRequest userLoginRequest);

    /**
     * @Param [userInsertSimpleRequest]
     * @Return com.bat.springcloud.response.CommonResult
     * @Author ZhengYu
     * @Description: 账号注册
     * @Date 2019/5/27
     */
    CommonResult registerAccount(UserInsertSimpleRequest userInsertSimpleRequest);

    /**
     * @Param [accountName]
     * @Return java.lang.String
     * @Author ZhengYu
     * @Description: 获取验证码文本
     * @Date 2019/5/29
     */
    String createVerificationCode(String accountName);
}