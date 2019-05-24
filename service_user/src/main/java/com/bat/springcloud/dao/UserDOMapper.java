package com.bat.springcloud.dao;

import com.bat.springcloud.entity.UserDO;

public interface UserDOMapper {
    int deleteByPrimaryKey(Integer accountUuid);

    int insert(UserDO record);

    int insertSelective(UserDO record);

    UserDO selectByPrimaryKey(Integer accountUuid);

    int updateByPrimaryKeySelective(UserDO record);

    int updateByPrimaryKey(UserDO record);
}