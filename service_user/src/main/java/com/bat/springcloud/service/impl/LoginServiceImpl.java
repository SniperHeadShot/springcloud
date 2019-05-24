package com.bat.springcloud.service.impl;

import com.bat.springcloud.enums.ConstantEnum;
import com.bat.springcloud.request.UserLoginRequest;
import com.bat.springcloud.response.CommonResult;
import com.bat.springcloud.service.LoginService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @ClassName LoginService
 * @Description 登陆服务实现
 * @Author ZhengYu
 * @Version: 1.0
 * @Create: 2019/5/24 18:54
 **/
@Service
public class LoginServiceImpl implements LoginService {

    /**
     * @param userLoginRequest
     * @Param [userLoginRequest]
     * @Return com.bat.springcloud.response.CommonResult
     * @Author ZhengYu
     * @Description: 校验验证码及账号密码是否正确
     * @Date 2019/5/24
     */
    @Override
    public CommonResult checkAccount(UserLoginRequest userLoginRequest) {
        // 校验参数完整性
        if (StringUtils.isEmpty(userLoginRequest.getVerificationCode()) || StringUtils.isEmpty(userLoginRequest.getUsername()) || StringUtils.isEmpty(userLoginRequest.getPassword())) {
            return new CommonResult(ConstantEnum.PARAMETER_VERIFICATION_FAIL);
        }
        // TODO 校验验证码
        if (!"GTX".equals(userLoginRequest.getVerificationCode())) {
            return new CommonResult(ConstantEnum.GLOBAL_FAIL, "验证码输入有误!");
        }
        // TODO 校验用户名和密码
        if (userLoginRequest.getUsername().equals(userLoginRequest.getPassword())) {
            return new CommonResult(ConstantEnum.GLOBAL_FAIL, "账户密码不正确!");
        }
        return new CommonResult(ConstantEnum.GLOBAL_SUCCESS);
    }
}
