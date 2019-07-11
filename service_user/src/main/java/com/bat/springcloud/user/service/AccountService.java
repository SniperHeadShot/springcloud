package com.bat.springcloud.user.service;

import com.bat.springcloud.user.request.UserInsertSimpleRequest;
import com.bat.springcloud.user.request.UserLoginRequest;
import com.bat.common.response.CommonResult;

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
     * @return CommonResult
     * @author ZhengYu
     * @date 2019/6/6
     */
    CommonResult checkAccount(UserLoginRequest userLoginRequest);

    /**
     * 账号注册
     *
     * @param userInsertSimpleRequest
     * @return CommonResult
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
