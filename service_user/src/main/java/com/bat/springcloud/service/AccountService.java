//package com.bat.springcloud.service;
//
//import com.bat.springcloud.entity.AccountDO;
//
//import java.util.List;
//
//public interface AccountService {
//
//    /**
//     * @Param [accountDORequest]
//     * @Return java.util.List<com.bat.springcloud.entity.AccountDO>
//     * @Author ZhengYu
//     * @Description: 获取账户数据列表
//     * @Date 2019/5/24
//     */
//    List<AccountDO> listAccounts(AccountDORequest accountDORequest);
//
//    /**
//     * @Param [userId]
//     * @Return com.bat.springcloud.entity.AccountDO
//     * @Author ZhengYu
//     * @Description: 获取某个账户数据
//     * @Date 2019/5/24
//     */
//    AccountDO getAccount(Long accountId);
//
//    /**
//     * @Param [accountDO]
//     * @Return java.lang.Boolean
//     * @Author ZhengYu
//     * @Description: 插入账户数据
//     * @Date 2019/5/24
//     */
//    Boolean insertAccount(AccountDO accountDO);
//
//    /**
//     * @Param [accountDOList]
//     * @Return java.lang.Boolean
//     * @Author ZhengYu
//     * @Description: 批量插入账户数据
//     * @Date 2019/5/24
//     */
//    Boolean insertBatchAccount(List<AccountDO> accountDOList);
//
//    /**
//     * @Param [accountDO]
//     * @Return java.lang.Boolean
//     * @Author ZhengYu
//     * @Description: 更新账户数据
//     * @Date 2019/5/24
//     */
//    Boolean updateAccount(AccountDO accountDO);
//
//    /**
//     * @Param [userId]
//     * @Return java.lang.Boolean
//     * @Author ZhengYu
//     * @Description: 删除账户数据
//     * @Date 2019/5/24
//     */
//    Boolean deleteAccount(Long userId);
//
//    /**
//     * @Param [userId]
//     * @Return java.lang.Boolean
//     * @Author ZhengYu
//     * @Description: 批量删除账户数据
//     * @Date 2019/5/24
//     */
//    Boolean deleteBatchAccount(List<Long> accountIdList);
//}
