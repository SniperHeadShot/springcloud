package com.bat.springcloud.dao;

import com.bat.springcloud.entity.AccountDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountDao {
    int deleteByPrimaryKey(Long accountId);

    int insert(AccountDO record);

    int insertSelective(AccountDO record);

    AccountDO selectByPrimaryKey(Long accountId);

    AccountDO selectByAccountName(String accountName);

    int updateByPrimaryKeySelective(AccountDO record);

    int updateByPrimaryKey(AccountDO record);
}