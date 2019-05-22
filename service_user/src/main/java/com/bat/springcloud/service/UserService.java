package com.bat.springcloud.service;

import com.bat.springcloud.domain.UserDO;
import com.bat.springcloud.request.UserDORequest;

import java.util.List;

public interface UserService {

    List<UserDO> getUserList(UserDORequest userDORequest);

    UserDO getUser(String userId);

    Boolean addUser(UserDO userDO);

    Boolean updateUser(UserDO userDO);

    Boolean deleteUser(String userId);
}
