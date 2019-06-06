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
     * 校验验证码及账号密码是否正确
     *
     * @param userLoginRequest
     * @return com.bat.springcloud.response.CommonResult
     * @author ZhengYu
     * @date 2019/6/6
     */
    CommonResult checkAccount(UserLoginRequest userLoginRequest);

    /**
     * 账号注册
     *
     * @param userInsertSimpleRequest
     * @return com.bat.springcloud.response.CommonResult
     * @author ZhengYu
     * @date 2019/6/6
     */
    CommonResult registerAccount(UserInsertSimpleRequest userInsertSimpleRequest);

    /**
     * 获取验证码文本
     *
     * @param accountName
     * @return java.lang.String
     * @author ZhengYu
     * @date 2019/6/6
     */
    String createVerificationCode(String accountName);
}
