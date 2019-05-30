package com.bat.springcloud.service.impl;

import com.bat.springcloud.constant.BaseSystemConfig;
import com.bat.springcloud.dao.AccountDao;
import com.bat.springcloud.entity.AccountDO;
import com.bat.springcloud.enums.ConstantEnum;
import com.bat.springcloud.request.UserInsertSimpleRequest;
import com.bat.springcloud.request.UserLoginRequest;
import com.bat.springcloud.response.CommonResult;
import com.bat.springcloud.service.AccountService;
import com.bat.springcloud.util.CommonUtil;
import com.bat.springcloud.util.RedisUtils;
import com.bat.springcloud.util.VerificationCodeUtil;
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

    @Autowired
    private RedisUtils redisUtils;

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
        // 校验验证码
        String verificationCode = this.redisUtils.getStringFromRedis(userLoginRequest.getUsername() + BaseSystemConfig.ACCOUNT_VERIFICATION_CODE_REDIS_SUFFIX);
        if (StringUtils.isEmpty(verificationCode) || !verificationCode.equals(userLoginRequest.getVerificationCode())) {
            return CommonResult.buildCommonResult(ConstantEnum.GLOBAL_FAIL, "验证码输入有误或验证码已过期!");
        }
        // 校验用户名和密码
        AccountDO selectByAccountName = this.accountDao.selectByAccountName(userLoginRequest.getUsername());
        if (selectByAccountName == null || !CommonUtil.md5Encrypt(userLoginRequest.getPassword()).equals(selectByAccountName.getAccountPassword())) {
            return CommonResult.buildCommonResult(ConstantEnum.GLOBAL_FAIL, "账户不存在或者密码不正确!");
        }
        return CommonResult.buildCommonResult(ConstantEnum.GLOBAL_SUCCESS,"登陆校验通过!");
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
        AccountDO selectByAccountName = this.accountDao.selectByAccountName(userInsertSimpleRequest.getAccountName());
        if (selectByAccountName != null) {
            return CommonResult.buildCommonResult(ConstantEnum.GLOBAL_FAIL, "账户已存在!");
        }
        AccountDO accountDO = new AccountDO();
        accountDO.setAccountUuid(CommonUtil.getRandomUuid());
        accountDO.setAccountName(userInsertSimpleRequest.getAccountName());
        accountDO.setAccountPassword(CommonUtil.md5Encrypt(userInsertSimpleRequest.getAccountPassword()));
        int result = this.accountDao.insertSelective(accountDO);
        return CommonResult.buildCommonResult(result > 0 ? ConstantEnum.GLOBAL_SUCCESS : ConstantEnum.SQL_EXECUTE_FAIL);
    }

    /**
     * @param accountName
     * @Param [accountName]
     * @Return java.lang.String
     * @Author ZhengYu
     * @Description: 获取验证码文本
     * @Date 2019/5/29
     */
    @Override
    public String createVerificationCode(String accountName) {
        String verificationCodeText = VerificationCodeUtil.createVerificationCodeText();
        // 将验证码和用户名绑定放入redis
        redisUtils.setStringToRedis(accountName + BaseSystemConfig.ACCOUNT_VERIFICATION_CODE_REDIS_SUFFIX, verificationCodeText, BaseSystemConfig.ACCOUNT_VERIFICATION_CODE_TIMEOUT_MILLIONS);
        return verificationCodeText;
    }
}
