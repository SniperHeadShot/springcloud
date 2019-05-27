package com.bat.springcloud.service.impl;

import com.bat.springcloud.dao.AccountDao;
import com.bat.springcloud.entity.AccountDO;
import com.bat.springcloud.enums.ConstantEnum;
import com.bat.springcloud.request.UserInsertSimpleRequest;
import com.bat.springcloud.request.UserLoginRequest;
import com.bat.springcloud.response.CommonResult;
import com.bat.springcloud.service.AccountService;
import com.bat.springcloud.util.CommonUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName AccountService
 * @Description 登陆服务实现
 * @Author ZhengYu
 * @Version: 1.0
 * @Create: 2019/5/24 18:54
 **/
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

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
            return CommonResult.buildCommonResult(ConstantEnum.PARAMETER_VERIFICATION_FAIL);
        }
        // TODO 校验验证码 这里可以使用Redis
        if (!"GTX".equals(userLoginRequest.getVerificationCode())) {
            return CommonResult.buildCommonResult(ConstantEnum.GLOBAL_FAIL, "验证码输入有误!");
        }
        // TODO 校验用户名和密码
        if (userLoginRequest.getUsername().equals(userLoginRequest.getPassword())) {
            return CommonResult.buildCommonResult(ConstantEnum.GLOBAL_FAIL, "账户密码不正确!");
        }
        return CommonResult.buildCommonResult(ConstantEnum.GLOBAL_SUCCESS);
    }

    /**
     * @param userInsertSimpleRequest
     * @Param [userInsertSimpleRequest]
     * @Return com.bat.springcloud.response.CommonResult
     * @Author ZhengYu
     * @Description: 账号注册
     * @Date 2019/5/27
     */
    @Override
    public CommonResult registerAccount(UserInsertSimpleRequest userInsertSimpleRequest) {
        if (StringUtils.isEmpty(userInsertSimpleRequest.getAccountName()) || StringUtils.isEmpty(userInsertSimpleRequest.getAccountPassword())) {
            return CommonResult.buildCommonResult(ConstantEnum.PARAMETER_VERIFICATION_FAIL);
        }
        AccountDO accountDO = new AccountDO();
        accountDO.setAccountUuid(CommonUtil.getRandomUuid());
        accountDO.setAccountName(userInsertSimpleRequest.getAccountName());
        accountDO.setAccountPassword(CommonUtil.md5Encrypt(userInsertSimpleRequest.getAccountPassword()));
        int result = this.accountDao.insertSelective(accountDO);
        return CommonResult.buildCommonResult(result > 0 ? ConstantEnum.GLOBAL_SUCCESS : ConstantEnum.SQL_EXECUTE_FAIL);
    }
}
